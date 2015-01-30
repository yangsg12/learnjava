package concorrency_cookbook.uncaught_exceptions;

import java.util.Random;

/**
 * Created by Yang on 2015/1/30.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true){
            result = 1000/(int) (random.nextDouble()*1000);
            System.out.printf("%s : %f",Thread.currentThread().getId(),result);
            if (Thread.currentThread().isInterrupted()){
                System.out.printf("%d is interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }
}
