package concorrency_cookbook.c5_fork_join.coustom_task;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by Yang on 2016/3/1.
 */
public abstract class MyWorkTask extends ForkJoinTask{
    private String name;

    public MyWorkTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Object value) {

    }

    @Override
    protected boolean exec() {

        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime()- startDate.getTime();

        System.out.printf("MyWorkTask : %s --- %d Milliseconds to compute.\n",name,diff);
        return true;
    }

    protected abstract void compute();
}
