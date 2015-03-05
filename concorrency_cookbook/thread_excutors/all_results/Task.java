package concorrency_cookbook.thread_excutors.all_results;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/13.
 */
public class Task implements Callable<Result> {
    private String name;
    public Task (String name){
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.printf("%s : started \n", this.name);

        long duration = (long) (Math.random() * 10);
        System.out.printf("%s : waiting %d 秒 for results" ,this.name,duration);
        TimeUnit.SECONDS.sleep(duration);

        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += (int) (Math.random() * 100);

        }

        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);

        System.out.println(this.name+" : ends");
        return result;
    }
}
