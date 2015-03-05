package concorrency_cookbook.thread_excutors.retrun_result;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/13.
 */
public class FactrialCal implements Callable<Integer> {
    private Integer number;

    public FactrialCal(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if ((number ==0) || (number ==1)){
            result = 1;
        }else {
            for (int i = 2; i <= number; i++) {
                result *=i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf(" %s : %d", Thread.currentThread().getName(), result);
        return result;
    }
}
