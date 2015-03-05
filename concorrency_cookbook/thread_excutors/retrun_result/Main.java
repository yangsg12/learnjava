package concorrency_cookbook.thread_excutors.retrun_result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/2/13.
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList= new ArrayList<Future<Integer>>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactrialCal cal = new FactrialCal(number);
            Future<Integer> result = executor.submit(cal);
            resultList.add(result);
        }

        do {
            System.out.printf("Main : complete task %d \n", executor.getCompletedTaskCount());
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                System.out.printf("Main : task %d  %s \n", i, result.isDone());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (executor.getCompletedTaskCount() < resultList.size());

        System.out.printf("Main : result \n");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Integer> result = resultList.get(i);
            Integer number = null;

            try {
                number = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.printf("Main: Task %d : %d \n",i,number);
        }

        executor.shutdown();
    }
}
