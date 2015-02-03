package concorrency_cookbook.syn_utilities.semaphore;


/**
 * Created by Yang on 2015/2/3.
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i=0;i<10;i++){
            threads[i] = new Thread(new Job(printQueue),"线程"+i);
        }

        for (int i=0;i<10;i++){
            threads[i].start();
        }
    }
}
