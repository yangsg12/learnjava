package concorrency_design.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by Yang on 2017/7/20.
 */
public class FutureMain {
    public static void main(String[] args) throws InterruptedException,ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("a data "));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(futureTask);
        System.out.println("请求完毕!!!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 =  " +futureTask.get());
    }
}
