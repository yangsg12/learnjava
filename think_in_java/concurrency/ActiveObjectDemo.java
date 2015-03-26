package think_in_java.concurrency;

import head_first.FlyBehavior;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/3/26.
 */
public class ActiveObjectDemo {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random random = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println(" sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("开始 "+ x+ "+ " +y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return exec.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                System.out.println("开始 "+ x+ "+ " +y);
                pause(2000);
                return x+y;
            }
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            results.add(d1.calculateFloat(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(d1.calculateInt(i, i));
        }
        System.out.println("all asynchronized calls made");
        while (results.size() > 0) {
            for (Future<?> future : results) {
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    results.remove(future);
                }
            }
            d1.shutdown();
        }
    }
}
