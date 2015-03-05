package concorrency_cookbook.thread_excutors;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/2.
 * 获取 首个 已结束任务的 运行 结果 ExecutorCompletionService
 */
public class MultipleServices {
    public static class Exp implements Callable{
        private double m;
        private int n;

        public Exp(double m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public Object call() throws Exception {
            double result =1;
            for (int i = 0; i < n; i++) {
                result *= m;  // 用乘法 计算 m的n立方
                Thread.sleep(10);
            }
            System.out.printf("%n 计算 %.02f raised to %d %n", m,n);
            return result;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Double>> tasks = new ArrayList<Callable<Double>>();

        for (int i = 0; i < 10; i++) {
            double m = Math.random()*10;
            int n = (int)(Math.random()*1000);
            System.out.printf("Create task for computing: "+"%.02f raised to %d %n",m,n);
            tasks.add(new Exp(m,n));
        }

        ExecutorCompletionService service = new ExecutorCompletionService(executor);
        for (Callable<Double> task : tasks) {
            service.submit(task);
        }

        // 检查 是否有任务结束
        Lock lock = new ReentrantLock();
        for (int i = 0; i < tasks.size(); i++) {
            try{
                lock.lock();
                Double d = (Double) service.take().get(); // take() 等待 第一个可用结果
                System.out.printf("结果 : %E %n",d);
                lock.unlock();
            }catch (InterruptedException   e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}
