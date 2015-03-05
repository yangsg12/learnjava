package concorrency_cookbook.syn_utilities.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by Yang on 2015/2/5.
 */
public class Corn {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);

        Thread tp = new Thread(producer);
        Thread tc = new Thread(consumer);

        tp.start();
        tc.start();
    }
}




/**
 * Created by Yang on 2015/2/5.
 */
 class Consumer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {

            try {
                buffer = exchanger.exchange(buffer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("消费者 :  "+buffer.size());

            System.out.printf("消费者 : cycle %d \n", cycle);
            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.printf("消费者 : %s \n",message);
                buffer.remove(0);

            }
            cycle++;

        }




    }
}


 class Producer implements Runnable {
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Producer( List<String> buffer,Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("生产者 : cycle %d \n", cycle);
            for (int j = 0; j < 10; j++) {
                String message = " 事件 "+((i*10)+j);
                System.out.printf("生产者 : %s \n",message);
                buffer.add(message);

            }


        }
        try {
            buffer = exchanger.exchange(buffer);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("生产者 :  "+buffer.size());
        cycle++;
    }
}
