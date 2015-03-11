package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/11.
 * allow give up on trying to acquire a lock.
 */
public class AttemptLock {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock() "+ captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLock attemptLock = new AttemptLock();
        attemptLock.untimed();
        attemptLock.timed();
        new Thread(){
            {setDaemon(false);}

            @Override
            public void run() {
                attemptLock.lock.lock();// ??
                System.out.println("获得锁");
            }
        }.start();

        Thread.yield();
        attemptLock.untimed();
        attemptLock.timed();


        Thread.yield();
        attemptLock.untimed();
        attemptLock.timed();
    }
}
