package concorrency_cookbook.syn_utilities.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/2.
 * 信号信 许可, 银行的几个柜台
 */
public class Bank {
    private final static int COUNT = 100;
    private final static Semaphore semaphore = new Semaphore(2,true);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            final int count = i;
            new Thread(){
                @Override
                public void run() {
                    try {
                        if (semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)){
                            try {
                                Teller.getService(count);
                            }finally {
                                semaphore.release();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}

class Teller{
    static public void getService(int i){
        System.out.println("正在服务: "+i);
        try {
            Thread.sleep((long)(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
