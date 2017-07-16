package concorrency_design;

/**
 * Created by Yang on 2017/7/15.
 */
public class AccoutingVol implements Runnable {
    static AccoutingVol instance = new AccoutingVol();
    static volatile int i = 0;

    public synchronized void   increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j<10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
