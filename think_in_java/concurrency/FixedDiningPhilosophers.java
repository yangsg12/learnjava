package think_in_java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/26.
 * 确保 最后一个 Philosopher 先拿起和放下 左边 的 chopstick
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception{
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);

        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }

        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, ponder));
            } else {
                executorService.execute(new Philosopher(chopsticks[0],chopsticks[i],i,ponder));
            }

        }

        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("press enter to quit ");
            System.in.read();
        }
        executorService.shutdownNow();
    }
}
