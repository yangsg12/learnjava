package concorrency_cookbook.c7.transfer_queue.monitor_executor;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2016/3/3.
 */
public class Task implements Runnable {
    private long milliseconds;

    public Task(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        System.out.printf("Task ---- %s : begin \n",Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task --- %s : end \n",Thread.currentThread().getName());
    }
}

class MonitorExecutor  {
    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(random.nextInt(10000));
            executor.submit(task);
        }

        for (int i = 0; i < 5; i++) {
            showLog(executor);
            TimeUnit.SECONDS.sleep(1);

        }
        executor.shutdown();

        for (int i = 0; i < 5; i++) {
            showLog(executor);
            TimeUnit.SECONDS.sleep(1);

        }
        executor.awaitTermination(1,TimeUnit.DAYS);
        System.out.printf("End \n");
    }

    private static void showLog(ThreadPoolExecutor executor){
        System.out.printf("*********************\n");
        System.out.printf("Main : Executor Log \n");
        System.out.printf("Main : Executor core pool size       : %d \n",executor.getPoolSize());
        System.out.printf("Main : Executor active counter       : %d \n", executor.getActiveCount());
        System.out.printf("Main : Executor task counter         : %d \n", executor.getTaskCount());
        System.out.printf("Main : Executor complete task counter: %d \n", executor.getCompletedTaskCount());
        System.out.printf("Main : Executor shut down            : %s \n", executor.isShutdown());
        System.out.printf("Main : Executor is terminating       : %s \n", executor.isTerminating());
        System.out.printf("Main : Executor terminated           : %s \n", executor.isTerminated());
        System.out.printf("********************\n");


    }
}