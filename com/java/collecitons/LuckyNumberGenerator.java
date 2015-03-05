package com.java.collecitons;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * Created by Yang on 2015/3/2.
 * 幸运数字产生器
 * 消费者 接受数字
 * TransferQueue
 */
public class LuckyNumberGenerator {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<String>();
        Thread producer = new Thread(new ProducerN(queue));
        //producer.setDaemon(true);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(new ConsumerN(queue));
            //consumer.setDaemon(true);
            consumer.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class ProducerN implements Runnable{
    private final TransferQueue<String> queue;

    public ProducerN(TransferQueue<String> queue) {
        this.queue = queue;
    }

    private String produce(){
        return "你的幸运数字 "+(new Random().nextInt(100));
    }

    @Override
    public void run() {
        while (true){
            if (queue.hasWaitingConsumer()) {
                queue.tryTransfer(produce());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ConsumerN implements Runnable{
    private final TransferQueue<String> queue;

    public ConsumerN(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Consumer "+Thread.currentThread().getName()+queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
