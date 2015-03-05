package com.java.collecitons;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Yang on 2015/3/2.
 * 线程 的 中断处理
 */
public class ControlledPrimeNumberGenerator {
    public static void main(String[] args) {
        Thread primeNumberGenerator = new Thread(new WorkThread());
        primeNumberGenerator.start();
        InputStreamReader in = new InputStreamReader(System.in);
        try {
            while (in.read() !='\n'){

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        primeNumberGenerator.interrupt();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (primeNumberGenerator.isInterrupted()){
            System.out.println("interrupted");
        }else {
            System.out.println(" number generator is not running");
        }

        Thread lazyWorker = new Thread(new LazyWorker());
        lazyWorker.start();
        System.out.println("lazy worker started");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lazyWorker.interrupt();
    }
}

class WorkThread implements Runnable{
    @Override
    public void run() {
        long i = 1;
        while (true){
            long j ;
            for (j = 2; j < i; j++) {
                long n = i%j;
                if (n==0){
                    break;
                }
            }
            if (i == j){
                System.out.print(" " + i);

            }
            i++;

            if (Thread.interrupted()) {
                System.out.println("stop prime generator");
                return;
            }
        }
    }
}

class LazyWorker implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("lazy worker :"+ e.toString());
        }
    }
}
