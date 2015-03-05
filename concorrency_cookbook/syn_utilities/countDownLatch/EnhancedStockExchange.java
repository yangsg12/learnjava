package concorrency_cookbook.syn_utilities.countDownLatch;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Yang on 2015/3/2.
 */
public class EnhancedStockExchange {
    public static void main(String[] args) {
        BlockingQueue<Integer> orderQueue = new LinkedBlockingDeque<Integer>();
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch stopSignal = new CountDownLatch(200);

        Seller seller = new Seller(orderQueue, startSignal, stopSignal);
        Thread[] sellerThreads = new Thread[100];
        for (int i = 0; i < 10; i++) {
            sellerThreads[i] = new Thread(seller);
            sellerThreads[i].start();
        }

        Buyer buyer = new Buyer(orderQueue,startSignal,stopSignal);
        Thread[] buyerThreads = new Thread[100];
        for (int i = 0; i < 10; i++) {
            buyerThreads[i] = new Thread(buyer);
            buyerThreads[i].start();
        }

        System.out.println(" 开始倒计数...");
        startSignal.countDown();

        try {
            while (System.in.read() == '\n'){
                System.exit(1);
            };
        } catch(IOException e){

        }
        System.out.println(" Terminating");
        for (Thread t : sellerThreads){
            t.interrupt();
        }

        for (Thread t : buyerThreads){
            t.interrupt();
        }

        try {
            stopSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Closing down");
    }
}

class Seller implements Runnable{
    private BlockingQueue<Integer> orderQueue;
    private boolean shutdownRequest  = false;
    private static int id ;
    private CountDownLatch startLatch, stopLatch;

    public Seller(BlockingQueue<Integer> orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (shutdownRequest == false){
            Integer quantity = (int)(Math.random()*100);
            try {
                orderQueue.put(quantity);
                System.out.println("---卖单 "+Thread.currentThread().getName()+" : "+quantity);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                shutdownRequest = true;
            }
        }

        stopLatch.countDown();
    }
}

class Buyer implements Runnable{
    private BlockingQueue<Integer> orderQueue;
    private boolean shutdownRequest = false;
    private CountDownLatch startLatch, stopLatch;

    public Buyer(BlockingQueue<Integer> orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (shutdownRequest == false){
            try{
                Integer quantity = (Integer)(orderQueue.take());
                System.out.println("+++ 买单 "+ Thread.currentThread().getName()+" : "+quantity);
            }catch (InterruptedException e){
                shutdownRequest = true;
            }
        }
        stopLatch.countDown();
    }
}