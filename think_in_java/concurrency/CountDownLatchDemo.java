package think_in_java.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/26.
 */
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch (InterruptedException e){

        }

    }

    public void doWork() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this + " 完成了");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

class WaitingTask implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("等待 任务 %1$-3d ", id);
    }
}
public class CountDownLatchDemo {
    static final int SIZE =100;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(latch));
        }

        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(latch));
        }

        System.out.println("所有任务 都 启动了");
        executorService.shutdown();
    }
}
