package concorrency_cookbook.syn_utilities.callable;


import java.text.DateFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/3/2.
 * callable 返回值
 * 年度 销售表, 月份 ,
 *         客户 __
 * 计算年销售额 客户数量很多时, 并行 计算
 * 线程 行  最后 各行 相加
 */
public class AnnualSaleCalc {
    private static int NUMBER_OF_CUSTOMERS = 100;
    private static int MONTHS =12;
    private static int saleMatrix[][];
    private static class Summer implements Callable {
        private int companyID;

        public Summer(int companyID) {
            this.companyID = companyID;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int col = 0; col < MONTHS; col++) {
                sum += saleMatrix[companyID][col];
            }
            System.out.printf("Totaling for client 1%02d completed \n", companyID);
            return sum;
        }
    }
    public static void main(String[] args) throws Exception {
            generatorMatrix();
            printMatrix();

            ExecutorService executor = Executors.newFixedThreadPool(10);
            Set<Future<Integer>> set = new HashSet<Future<Integer>>();
            for (int row = 0; row < NUMBER_OF_CUSTOMERS; row++) {
                Callable<Integer> callable = new Summer(row);
                Future<Integer> future = executor.submit(callable);
//                FutureTask<Integer> future = new FutureTask<Integer>(callable);
//                future.run();
                set.add(future);
            }

            int sum = 0;
            for (Future<Integer> future :set){
                sum += future.get();
            }
            System.out.printf("%n The annual turnover (bags) : %s%n%n", sum);
            executor.shutdown();
        }

        private static void generatorMatrix() {
            saleMatrix = new int [NUMBER_OF_CUSTOMERS][MONTHS];
            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
                for (int j = 0; j < MONTHS; j++) {
                    saleMatrix[i][j] = (int) (Math.random() * 100);
                }
            }

        }

        private static void printMatrix() {
            System.out.print("\t\t");
            String[] monthDisplayNames = (new DateFormatSymbols().getShortMonths());
            for (String str : monthDisplayNames){
                System.out.printf(" %8s", str);
            }
            System.out.printf("%n%n");

            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
                System.out.printf("Client ID : 1%02d",i);
                for (int j = 0; j < MONTHS; j++) {
                    System.out.printf("%8d", saleMatrix[i][j]);
                }
                System.out.println();
            }
            System.out.printf("%n%n");
        }
}

