package concorrency_design;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Yang on 2017/7/17.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0;i<10000;i++) {
                array.getAndIncrement(i % array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for (int i =0;i<10;i++) {
            threads[i] = new Thread(new AddThread());
        }

        for (int i =0;i<10;i++) {
            threads[i].start();
        }

        for (int i =0;i<10;i++) {
            threads[i].join();
        }

        System.out.println(array);
    }
}
