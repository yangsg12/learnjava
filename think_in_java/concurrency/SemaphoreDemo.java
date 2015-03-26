package think_in_java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/26.
 */

class CheckoutTask<T> implements Runnable {
    private static int counter = 0 ;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkout();
            System.out.println(this + " checkout " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " check in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "Check out task " + id + " ";
    }
}

public class SemaphoreDemo {
    final static int SIZE = 25;

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println(" All CheckoutTask created +++");
        List<Fat> fatList = new ArrayList<Fat>();
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkout();
            System.out.println(i + " main() thread check out");
            fat.operation();
            fatList.add(fat);
        }

        Future<?> blocked = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkout();
                } catch (InterruptedException e) {
                    System.out.println("checkout() interrupted");
                }
            }
        });

        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println(" checking in objects in " + fatList);
        for (Fat fat : fatList) {
            pool.checkIn(fat);
        }
        // second check in ignored
        for (Fat fat : fatList) {
            pool.checkIn(fat);
        }

        executorService.shutdown();
    }
}
