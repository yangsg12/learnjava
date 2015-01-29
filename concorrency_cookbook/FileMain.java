package concorrency_cookbook;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/1/29.
 */
public class FileMain {
    public static void main(String[] args) {
        FileClock fileClock = new FileClock();
        Thread thread = new Thread(fileClock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
