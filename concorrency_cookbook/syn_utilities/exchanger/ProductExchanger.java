package concorrency_cookbook.syn_utilities.exchanger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by Yang on 2015/3/2.
 * exchanger 实现 生产 消费
 */
public class ProductExchanger {
    public static Exchanger <List<Integer>> exchanger = new Exchanger<List<Integer>>(); //static 生产者,消费者 线程 访问
    // 无需创建 类实例.

    public static void main(String[] args) {

        Thread producer = new Thread(new Producer1());
        Thread consumer = new Thread(new Consumer1());

        producer.start();
        consumer.start();


        try {
            while (System.in.read() != '\n'){

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        producer.interrupt();
        consumer.interrupt();
    }
}


 class Producer1 implements Runnable{

     private static List<Integer> buffer = new ArrayList<Integer>();
     private boolean okToRun = true;
     private final int BUFFER_SIZE = 10;
    @Override
    public void run() {
        int j = 0;
        while (okToRun){
            if (buffer.isEmpty()){
                for (int i = 0; i < BUFFER_SIZE; i++) {
                    buffer.add((int) (Math.random() * 100));
                }
                try {
                    Thread.sleep((int)(Math.random()*1000));
                    for (int i : buffer){
                        System.out.print(i + " ,");
                    }
                    System.out.println("生产者++++");
                    System.out.println("Exchanging...");
                    buffer = ProductExchanger.exchanger.exchange(buffer);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    okToRun = false;
                }
            }
        }
    }
}

class Consumer1 implements Runnable{
    private static List<Integer> buffer = new ArrayList<Integer>();
    private boolean okToRun = true;

    @Override
    public void run() {
        while (okToRun){
            try{
                if (buffer.isEmpty()) {
                    buffer = ProductExchanger.exchanger.exchange(buffer);
                    System.out.println("--消费者 ");
                    for (int i : buffer){
                        System.out.print(i+ " ,");
                    }
                    System.out.println();
                    Thread.sleep((int)(Math.random()*1000));
                    buffer.clear();
                }
            }catch (InterruptedException e){
                okToRun = false;
            }
        }
    }
}