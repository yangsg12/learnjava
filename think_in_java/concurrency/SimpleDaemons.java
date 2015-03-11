package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/11.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+" "+ this);
        }
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println(" All daemons started !!!");
        TimeUnit.MILLISECONDS.sleep(200);
    }
}
