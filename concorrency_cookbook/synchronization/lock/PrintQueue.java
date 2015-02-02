package concorrency_cookbook.synchronization.lock;

import concorrency_cookbook.synchronization.block.Main;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/2/2.
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock();
    public void printJob(Object document){
        queueLock.lock();
        long duration = (long) (Math.random() * 10000);
        System.out.println(Thread.currentThread().getName()
                + " : 打印队列 : 本次打印耗时 "+ (duration/1000)+ "秒");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finally {
            queueLock.unlock();
        }
    }
}
