package concorrency_cookbook;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/1/29.
 */
public class FileClock implements Runnable {
    @Override
    public void run() {
        for (int i =0; i<10;i++){
            System.out.printf("%s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("The file clock is interrupted.");
            }
        }
    }
}
