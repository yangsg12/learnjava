package com.java.collecitons;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Yang on 2015/3/2.
 * 阻塞队列
 */
public class StockExchange {
    public static void main(String[] args) {
        System.out.println(" press enter to terminate ");
        BlockingQueue<Integer> orderQueue = new LinkedBlockingDeque<Integer>();

        Seller seller = new Seller(orderQueue);
        Thread[] sellerThreads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            sellerThreads[i] = new Thread(seller);
            sellerThreads[i].start();
        }

        Buyer buyer = new Buyer(orderQueue);
        Thread[] buyerThreads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            buyerThreads[i] = new Thread(buyer);
            buyerThreads[i].start();
        }

        try {
            while (System.in.read() == '\n'){
                System.exit(1);
            };
        }catch (IOException e){

        }

        System.out.println("Terminating");

        for (Thread t: sellerThreads){
            t.interrupt();
        }

        for (Thread t: buyerThreads){
            t.interrupt();
        }
    }
}

class Seller implements Runnable{
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;

    public Seller(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest == false) {
            Integer quantity = (int) (Math.random() * 100);
            try {
                orderQueue.put(quantity);
                System.out.println("卖单  ---:"+ Thread.currentThread().getName()+" :"+quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }
        }
    }
}

class Buyer implements Runnable{
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;

    public Buyer(BlockingQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        while (shutdownRequest = false){
            try {
                Integer quantity = (Integer)orderQueue.take();
                System.out.println("买单 : "+ Thread.currentThread().getName()+" :"+quantity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                shutdownRequest = true;
            }
        }
    }
}
