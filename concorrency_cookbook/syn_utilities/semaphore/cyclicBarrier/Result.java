package concorrency_cookbook.syn_utilities.semaphore.cyclicBarrier;

/**
 * Created by Yang on 2015/2/3.
 */
public class Result {
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
