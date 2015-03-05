package com.java.collecitons;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/2.
 * 两个球桶 两个线程
 * 一线程 左桶 到右桶 , 另一个相反
 * 球桶的 独占访问权
 */
public class BucketBallGame {
    private int bucket[] ={10000,10000};
    private static boolean RIGHT_TO_LEFT;
    ReentrantLock lock = new ReentrantLock();  //加锁

    public static void main(String[] args) {
        new BucketBallGame().doTransfers();
    }

    private void doTransfers() {
        for (int i = 0; i < 10; i++) {
            new Thread(new TransferThread (!RIGHT_TO_LEFT)).start();
            new Thread(new TransferThread (RIGHT_TO_LEFT)).start();
        }
    }

    public  void  transfer(boolean direction, int numToTransfer){ //同步锁 去掉 synchronized 不同步了
        lock.lock();
        if (direction == RIGHT_TO_LEFT){
            bucket[0] += numToTransfer;
            bucket[1] -= numToTransfer;
        }
        else {
            bucket[0] -= numToTransfer;
            bucket[1] += numToTransfer;
        }
        lock.unlock();
        System.out.println("Total : "+ (bucket[0]+bucket[1]));
    }

    private class TransferThread implements Runnable{
        private boolean direction;

        public TransferThread(boolean direction) {
            this.direction = direction;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                transfer(direction,(int)(Math.random()*2000)); // 0 to 1999 随机数
                try {
                    Thread.sleep((int)(Math.random()*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
