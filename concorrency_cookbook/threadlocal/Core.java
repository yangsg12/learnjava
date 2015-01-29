package concorrency_cookbook.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/1/29.
 */
public class Core {
    public static void main(String[] args) {
        UnsafeTask unsafeTask = new UnsafeTask();
        for (int i =0;i<10;i++){
            Thread thread = new Thread(unsafeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("===============================");

        SafeTask safeTask  = new SafeTask();
        for (int i =0;i<10;i++){
            Thread thread = new Thread(safeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
