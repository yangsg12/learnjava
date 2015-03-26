package think_in_java.concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/25.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + " 在思考人生...");
                pause();
                System.out.println(this + " 右白虎");
                right.take();
                System.out.println(this + " 左青龙");
                left.take();
                System.out.println(this + " 开吃了!!!");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return "哲学家 "+id;
    }
}
