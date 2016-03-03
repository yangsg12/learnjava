package concorrency_cookbook.c5_fork_join;

import java.util.concurrent.*;

/**
 * Created by Yang on 2016/3/1.
 */
public class MyWorkerThread extends ForkJoinWorkerThread {
    private static ThreadLocal<Integer> taskCount = new ThreadLocal<Integer>();

    public MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("My Work Thread %d : Initial task count. \n", getId());
        taskCount.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkThread %d :  %d \n", getId(),taskCount.get());
        super.onTermination(exception);

    }


    public void addTask(){
        int counter = taskCount.get().intValue();
        counter++;
        taskCount.set(counter);
    }
}


class MyWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory{
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkerThread(pool);
    }
}


class MyRecursiveTask extends RecursiveTask<Integer>{
    private int[] array;
    private int start, end;

    public MyRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer ret =0 ;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        //return ret;
        return ret;
    }

    private Integer addResult(MyRecursiveTask task1,MyRecursiveTask task2){
        int value;
        try {
            value = task1.get().intValue()+task2.get().intValue();

        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

}

class Main2{
    public static void main(String[] args) throws Exception{
       MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        ForkJoinPool pool  = new ForkJoinPool(4,factory,null,false);
        int [] array  = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }


        MyRecursiveTask task = new MyRecursiveTask(array,0,array.length);
        pool.execute(task);
        task.join();
        pool.shutdown();

        pool.awaitTermination(1,TimeUnit.DAYS);
        System.out.printf("main : Result %d \n", task.get());
        System.out.printf("结束了啊， 我真厉害");

    }
}
