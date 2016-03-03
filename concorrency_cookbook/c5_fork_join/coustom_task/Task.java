package concorrency_cookbook.c5_fork_join.coustom_task;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Yang on 2016/3/1.
 */
public class Task extends MyWorkTask {
    private int [] array;
    private int start, end;

    public Task(String name, int[] array, int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start >100){
            int mid = (end + start)/2;
            Task task1 = new Task(this.getName()+"1",array,start,mid);
            Task task2 = new Task(this.getName()+"2",array,mid,end);
            invokeAll(task1,task2);
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CoustomTaskMain {
    public static void main(String[] args) {
        int [] array = new int[10000];
        ForkJoinPool pool = new ForkJoinPool();
        Task task = new Task("custom task",array,0,array.length);
        pool.invoke(task);
        pool.shutdown();
        System.out.printf("End \n");
    }
}
