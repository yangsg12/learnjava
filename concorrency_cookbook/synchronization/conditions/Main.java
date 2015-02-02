package concorrency_cookbook.synchronization.conditions;

/**
 * Created by Yang on 2015/2/2.
 */
public class Main {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread1.start();
        thread2.start();
    }
}
