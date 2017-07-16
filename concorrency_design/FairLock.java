package concorrency_design;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2017/7/16.
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getId() + " 获得锁");
            } finally {
                fairLock.unlock();
            }

        }
    }

    public static void main(String[] args) {
        FairLock l1 = new FairLock();
        Thread t1 = new Thread(l1, "Thread_t1");
        Thread t2 = new Thread(l1, "Thread_t2");
        t1.start();
        t2.start();

    }
}
