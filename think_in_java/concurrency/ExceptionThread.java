package think_in_java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yang on 2015/3/11.
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}
