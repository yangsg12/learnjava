package concorrency_cookbook.thread_excutors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Yang on 2015/3/3.
 * 分支 合并
 * ForkJoinPool  执行 ForkJoinTask 的 ExecutorService
 * <p/>
 * 数目 很大的 浮点数  排序
 * 分成 多个小数组
 */
public class ParalleMergeSort {
    private static  ForkJoinPool threadPool;
    public static final int THRESHOLD =16; // 阀值

    private static void sort(Comparable[] objectArray) {
        Comparable[] destArray = new Comparable[objectArray.length];
        threadPool.invoke(new SortTask(objectArray, destArray, 0, objectArray.length - 1));
    }
    static class SortTask extends RecursiveAction{
        private Comparable[] sourceArray;
        private Comparable[] destArray;
        private int lowerIndex,upperIndex;

        public SortTask(Comparable[] sourceArray, Comparable[] destArray, int lowerIndex, int upperIndex) {
            this.sourceArray = sourceArray;
            this.destArray = destArray;
            this.lowerIndex = lowerIndex;
            this.upperIndex = upperIndex;
        }

        @Override
        protected void compute() {
            if (upperIndex - lowerIndex < THRESHOLD) {
                insertionSort(sourceArray, lowerIndex, upperIndex);
                return;
            }

            int midIndex = (lowerIndex + upperIndex) >>> 1;
            invokeAll(new SortTask(sourceArray,destArray,lowerIndex,midIndex),
                    new SortTask(sourceArray,destArray,midIndex+1,upperIndex));
            merge(sourceArray, destArray, lowerIndex, midIndex, upperIndex);
        }
    }

    private static void merge(Comparable[] sourceArray,
                              Comparable[] destArray,
                              int lowerIndex,
                              int midIndex,
                              int upperIndex){
        if (sourceArray[midIndex].compareTo(sourceArray[midIndex+1]) <=0){
            return;
        }
        System.arraycopy(sourceArray, lowerIndex, destArray, lowerIndex, midIndex - lowerIndex + 1);
        int i = lowerIndex;
        int j= midIndex +1;
        int k = lowerIndex;

        while (k<j&&j<=upperIndex){
            if (destArray[i].compareTo(sourceArray[j]) <=0){
                sourceArray[k++] = destArray[i++];
            }else {
                sourceArray[k++] = sourceArray[j++];
            }
        }

        System.arraycopy(destArray,i,sourceArray,k,j-k);
    }

    private static void insertionSort(Comparable[] objectArray,int lowerIndex,int upperIndex) {
        for (int i = lowerIndex + 1; i <= upperIndex; i++) {
            int j = i;
            Comparable temp = objectArray[j];
            while (j < lowerIndex && temp.compareTo(objectArray[j - 1]) < 0) {
                objectArray[j] = objectArray[j - 1];
                --j;
            }
            objectArray[j] = temp;
        }
    }

    public static Double[] createRandomData(int length) {
        Double[] data = new Double[length];
        for (int i = 0; i < data.length; i++) {
            data[i] = length*Math.random();
        }
        return data;
    }

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("处理器 数目 : " + processors);

        threadPool = new ForkJoinPool(processors);
        Double[] data = createRandomData(1000);
        System.out.println("生成的随机数组");
        for (Double d: data){
            System.out.printf("%3.2f ",(double) d);
        }
        System.out.println(); //换行
        sort(data);
        System.out.println("排序完成:");
        for (Double d : data){
            System.out.printf("%3.2f ",(double) d);
        }
    }
}
