package concorrency_cookbook.synchronization.lock;

/**
 * Created by Yang on 2015/2/2.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue){
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("线程 %s : 开始打印------\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("线程 %s : 文档打印好了啊-\n", Thread.currentThread().getName());
    }
}
