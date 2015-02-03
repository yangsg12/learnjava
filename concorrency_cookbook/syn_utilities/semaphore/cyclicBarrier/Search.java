package concorrency_cookbook.syn_utilities.semaphore.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Yang on 2015/2/3.
 */
public class Search implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Result result;
    private int number;

    private final CyclicBarrier cyclicBarrier;

    public Search( int firstRow, int lastRow, MatrixMock mock, Result result, int number,CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.result = result;
        this.number = number;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf(" %s processing lines form %d to %d \n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i=firstRow;i<lastRow;i++) {
            int row[] = mock.getRow(i);
            counter = 0;
            for (int j=0;j<row.length;j++){
                if (row[j] == number){
                    counter++;
                }
            }
            result.setData(i,counter);
        }
        System.out.printf(" %s lines processed \n",Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
