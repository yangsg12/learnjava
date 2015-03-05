package concorrency_cookbook.syn_utilities.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Yang on 2015/2/3.
 *
 * 报错了，不知道哪出问题！！！
 */
public class Main {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT =2000;

        MatrixMock mock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        Result result = new Result(ROWS);
        Grouper grouper = new Grouper(result);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, grouper);
        Search search[] = new Search[PARTICIPANTS];

        for (int i=0;i<PARTICIPANTS;i++) {
            search[i] = new Search(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT + LINES_PARTICIPANT), mock, result, 5, cyclicBarrier);
            Thread thread = new Thread(search[i]);
            thread.start();
        }
        System.out.println("Main 执行完了！！！");
    }
}



/**
 * Created by Yang on 2015/2/3.
 */
 class Grouper implements Runnable {
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

class MatrixMock {
    private int data[][] ;

    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random() ;
        for (int i=0;i<size;i++){
            for (int j=0;j<length;j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j]==number){
                    counter++;
                }
            }
        }

        System.out.printf("Mock : 有 %d 个数 在生成的 数组里\n ",counter,number);
    }

    public int[] getRow(int row){
        if ((row > 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }


}


class Result {
    private int[] data;
    public Result(int size) {
        data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData(){
        return data;
    }
}

class Search implements Runnable {
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

