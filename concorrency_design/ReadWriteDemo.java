package concorrency_design;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Yang on 2017/7/16.
 */
public class ReadWriteDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Lock readLock = rwLock.readLock();
    private static Lock writeLock = rwLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) {

        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            return value;

        }
    }

    public void handleWrite(Lock lock, int index) {

        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ReadWriteDemo demo = new ReadWriteDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                    demo.handleRead(lock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                demo.handleWrite(writeLock,new Random().nextInt());
                demo.handleWrite(lock, new Random().nextInt());
            }
        };

        for (int i = 0;i<18;i++) {
            new Thread(readRunnable).start();

        }

        for (int i = 0; i< 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
