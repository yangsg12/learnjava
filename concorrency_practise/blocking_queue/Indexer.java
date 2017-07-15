package concorrency_practise.blocking_queue;

import java.io.File;
import java.util.concurrent.BlockingDeque;

/**
 * Created by Yang on 2016/8/4.
 */
public class Indexer implements Runnable {
    private final BlockingDeque<File> queue;

    public Indexer(BlockingDeque<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                indexFile(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void indexFile(File take) {
    }
}
