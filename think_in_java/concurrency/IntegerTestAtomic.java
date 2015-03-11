package think_in_java.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yang on 2015/3/11.
 */
public class IntegerTestAtomic implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    public int getValue(){return i.get();}
    private void evenIncrement(){ i.addAndGet(2);}

    @Override
    public void run() {
        while (true) {
            evenIncrement();

        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("中止了");
                System.exit(0);
            }
        },5000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        IntegerTestAtomic ait = new IntegerTestAtomic();
        executorService.execute(ait);
        while (true) {
            int val = ait.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
