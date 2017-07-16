package concorrency_design;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Yang on 2017/7/16.
 */

public class CountTask extends RecursiveTask {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Object compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i< end;i++) {
                sum +=i;
            }
        }else {
            long step = (end -start)/100;
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne >end) lastOne = end;
                CountTask subTask = new CountTask(pos, lastOne);
                pos += step+1;
                subTasks.add(subTask);
                subTask.fork();
            }

            for (CountTask t : subTasks) {
                //sum +=t.join();
            }


        }


        return sum;
    }
}
