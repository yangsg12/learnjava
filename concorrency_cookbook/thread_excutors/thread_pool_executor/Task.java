package concorrency_cookbook.thread_excutors.thread_pool_executor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/12.
 */
public class Task implements Runnable {
    private Date initDate;
    private String name;

    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf(" %s Task create %s 时间: %s\n",Thread.currentThread().getName(),name,initDate);
        System.out.printf(" %s Task started %s 时间: %s\n",Thread.currentThread().getName(),name,new Date());

        Long duration = (long) (Math.random() * 10);
        try {
            System.out.printf(" %s Task %s 进行了 %d 秒\n", Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf(" %s Task 结束了. %s 时间: %s\n",Thread.currentThread().getName(),name,new Date());
    }
}
