package concorrency_design.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yang on 2017/7/20.
 */
public class SearchTask implements Callable<Integer> {
    int beg, end, searchValue;

    static AtomicInteger result = new AtomicInteger(-1);
    static ExecutorService pool = Executors.newCachedThreadPool();
    static int[] arr;
    static final int  THREAD_NUM =2;

    public SearchTask(int beg, int end, int searchValue) {
        this.beg = beg;
        this.end = end;
        this.searchValue = searchValue;
    }

    @Override
    public Integer call() throws Exception {
        int re = search(beg, end, searchValue);
        return re;
    }

    public static int search(int beg, int end, int searchValue) {
        int i = 0;
        for (i = beg;i < end;i++) {
            if (result.get() >= 0) {
                return result.get();
            }
            return i;
        }
        return -1;
    }


    public static int pSearch(int searchValue) throws InterruptedException, ExecutionException{
        int subArray = arr.length/THREAD_NUM +1;
        List<Future<Integer>> re = new ArrayList<>();
        for (int i = 0;i<arr.length;i+=subArray) {
            int end = i+ subArray;
            if (end >= arr.length) {
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(i, end, searchValue)));
        }
        for (Future<Integer> fu : re) {
            if (fu.get() >= 0) {
                return fu.get();
            }
        }

        return -1;
    }
}
