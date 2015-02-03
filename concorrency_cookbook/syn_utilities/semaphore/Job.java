package concorrency_cookbook.syn_utilities.semaphore;

/**
 * Created by Yang on 2015/2/3.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf(" %s 将要打印 \n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf(" %s 文档已打印完成 \n", Thread.currentThread().getName());

    }
}
