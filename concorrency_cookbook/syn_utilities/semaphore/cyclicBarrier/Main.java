package concorrency_cookbook.syn_utilities.semaphore.cyclicBarrier;

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
