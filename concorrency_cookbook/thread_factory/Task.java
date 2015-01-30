package concorrency_cookbook.thread_factory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/1/30.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
