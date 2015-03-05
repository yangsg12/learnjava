package com.java.collecitons;

/**
 * Created by Yang on 2015/3/2.
 * 生产者, 消费者  生产球
 * 通知到对方
 */
public class ProducerConsumerGame {
    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        new Thread(new Producer(bucket)).start();
        new Thread(new Consumer(bucket)).start();
    }
}

final class Consumer implements Runnable{
    private Bucket bucket;

    public Consumer(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bucket.get();
        }
    }
}

final class Producer implements Runnable{
    private Bucket bucket;

    public Producer(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bucket.put((int)(Math.random()*100));
        }
    }
}

class Bucket{
    private int packOfBalls;
    private boolean available = false;

    public synchronized int get(){
        if (available == false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer got : "+ packOfBalls);
        available = false;
        notify();
        return packOfBalls;
    }

    public synchronized void put(int packOfBalls){
        if (available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.packOfBalls = packOfBalls;
        available = true;
        System.out.println("++++++++++++");
        System.out.println(" Producer put : " + packOfBalls);
        notify();
    }
}
