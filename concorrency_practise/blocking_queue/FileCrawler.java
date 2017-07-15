package concorrency_practise.blocking_queue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;

/**
 * Created by Yang on 2016/8/4.
 */
public class FileCrawler implements Runnable {
    private final BlockingDeque<File> fileBlockingDeque;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingDeque<File> fileBlockingDeque, FileFilter fileFilter, File root) {
        this.fileBlockingDeque = fileBlockingDeque;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void crawl(File root) throws InterruptedException{
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    fileBlockingDeque.put(entry);
                }
            }
        }

    }

    private boolean alreadyIndexed(File entry) {
        return true;
    }
}
