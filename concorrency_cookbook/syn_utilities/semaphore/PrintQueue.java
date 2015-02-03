package concorrency_cookbook.syn_utilities.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/2/3.
 */
public class PrintQueue {
    private final Semaphore semaphore ;
    private boolean freePrinters[]; //多个资源
    private Lock lock;

    public PrintQueue() {
        // semaphore = new Semaphore(1);
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        for (int i=0;i<3;i++){
            freePrinters[i] = true;
        }

        lock = new ReentrantLock();

    }

    public void printJob(Object document){
        try {
            semaphore.acquire();

            int assignedPrinter = getPrint();
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s 打印队列：打印机 %d 正在打印，进行了 %d 秒\n",
                    Thread.currentThread().getName(), assignedPrinter,duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    private int getPrint() {
        int ret =-1;
        try {
            lock.lock();
            for (int i=0;i<freePrinters.length;i++){
                if (freePrinters[i]){
                ret = i;
                freePrinters[i] = false;
                break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return ret;
    }
}
