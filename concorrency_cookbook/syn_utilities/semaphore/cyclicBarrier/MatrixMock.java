package concorrency_cookbook.syn_utilities.semaphore.cyclicBarrier;

import java.util.Random;

/**
 * Created by Yang on 2015/2/3.
 */
public class MatrixMock {
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
