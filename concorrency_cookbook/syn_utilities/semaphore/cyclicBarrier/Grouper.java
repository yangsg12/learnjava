package concorrency_cookbook.syn_utilities.semaphore.cyclicBarrier;

/**
 * Created by Yang on 2015/2/3.
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf(" Grouper processing results ... \n");
        int data[] = result.getData();
        for (int number: data){
            finalResult += number;
        }
        System.out.printf(" Total result is %d \n", finalResult);
    }
}
