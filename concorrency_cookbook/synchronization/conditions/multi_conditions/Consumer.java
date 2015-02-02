package concorrency_cookbook.synchronization.conditions.multi_conditions;

import java.util.Random;

/**
 * Created by Yang on 2015/2/2.
 */
public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        Random random = new Random() ;
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
