package concorrency_cookbook.thread_excutors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/3/2.
 * 可取消任务 的 股票交易 系统
 */
public class StockOrderProcessor {
    static final int MAX_NUMBER_OF_ORDERS = 10000;
    static private ExecutorService executor = Executors.newFixedThreadPool(100);
    static private List<Future> ordersToProcess = new ArrayList<Future>();
    private static class OrderExecutor implements Callable{
        int id = 0;
        int count = 0;
        public OrderExecutor(int id){
            this.id = id;
        }
        @Override
        public Object call() throws Exception {
            while (count< 50){
                count++;
                Thread.sleep(new Random(System.currentTimeMillis()%100).nextInt(10));
                System.out.println("成功执行订单: "+id);
            }
            return id;
        }
    }

    public static void main(String[] args) {
        System.out.printf(" Submitting %d trades %n", MAX_NUMBER_OF_ORDERS);
        for (int i = 0; i < MAX_NUMBER_OF_ORDERS; i++) {
            SubmitOrder(i);
        }

        new Thread(new EvilThread(ordersToProcess)).start();
        System.out.println("Cancelling a few orders at random");
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Checking status before shutdown ");
        int count = 0;
        for (Future f : ordersToProcess){
            if (f.isCancelled()){
                System.out.println("订单 ---"+f.toString());
                count++;
            }
        }
        System.out.printf("%d trades cancelled %n", count);
        executor.shutdown();
    }

    private static void SubmitOrder(int id) {
        Callable<Integer> callable = new OrderExecutor(id);
        ordersToProcess.add(executor.submit(callable));
    }

}

class EvilThread implements Runnable{
    private List<Future> orderToProcess;

    public EvilThread(List<Future> orderToProcess) {
        this.orderToProcess = orderToProcess;
    }

    @Override
    public void run() {
        Random myNextKill = new Random(System.currentTimeMillis() % 100);
        for (int i = 0; i < 100; i++) {
            int index = myNextKill.nextInt(StockOrderProcessor.MAX_NUMBER_OF_ORDERS);
            boolean cancel = orderToProcess.get(index).cancel(true);
            if (cancel){
                System.out.println(" 取消订单 成功: "+ index);
            }else {
                System.out.println("取消订单 失败: "+ index);
            }

            try {
                Thread.sleep(myNextKill.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
