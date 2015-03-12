package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/12.
 */
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup  "+id);
    }

    public void cleanup() {
        System.out.println("Cleaning up ---> "+id);
    }

}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            NeedsCleanup n1 = new NeedsCleanup(1);
            System.out.println("Sleeping");
            try {
                TimeUnit.SECONDS.sleep(1);
                NeedsCleanup n2 = new NeedsCleanup(2);
                try {
                    System.out.println("计算...");
                    for (int i = 0; i < 250000; i++) {
                        d = d + (Math.PI + Math.E) / d;
                        System.out.println("计算 OK.");
                    }
                } finally {
                    n2.cleanup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                n1.cleanup();
            }

            System.out.println("Exiting via InterruptedException");
        }
    }
}
public class InterruptingIdiom {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Blocked3());
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }
}
