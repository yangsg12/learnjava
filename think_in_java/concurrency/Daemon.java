package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/11.
 */
public class Daemon implements Runnable {
    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println(" DaemonSpawn "+i+ " started!!!");
        }

        for (int i = 0; i < threads.length; i++) {
            System.out.println(" threads [ "+i+" ].isDaemon() = "+threads[i].isDaemon());
        }
        while (true) {
            Thread.yield();
        }
    }
}


class DaemonSpawn implements Runnable{
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

class Daemons{
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();
        System.out.println("thread.isDaemon() = " + thread.isDaemon());
        TimeUnit.SECONDS.sleep(1);
    }
}