package concorrency_cookbook.syn_utilities.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Yang on 2015/3/2.
 * 集合点, 数量 await()
 * ln(1-X) ln 为自然对数 = -(x+ x2/2 + x3/3 + x4/4 ...) where |x| <1
 */
public class NatureLogCalc {
    private static final int numberOfTerms = 10;
    private static double[] termArray = new double[numberOfTerms];
    private static final float x = 0.2f;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(numberOfTerms, new Runnable() {
            @Override
            public void run() {
                System.out.println("computing series sum");
                double sum = 0;
                for (double term : termArray){
                    sum += term;
                }
                System.out.println("ln(1-"+x+" equals "+ -sum);
            }
        });

        for (int i = 0; i < numberOfTerms; i++) {
            new Thread(new TermCalc(barrier,i)).start();
        }

        System.out.println(" 等待...");
    }

    private static class TermCalc implements Runnable{
        private int termIndex;
        private CyclicBarrier barrier;

        public TermCalc(CyclicBarrier barrier,int termIndex ) {
            this.barrier = barrier;
            this.termIndex = termIndex;
        }

        @Override
        public void run() {
            double result = Math.pow(x, termIndex + 1) / (termIndex + 1);
            termArray[termIndex] = result;
            System.out.println("Term "+ (termIndex+1) +" : "+ result);

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

