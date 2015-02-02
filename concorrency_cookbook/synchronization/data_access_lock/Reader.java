package concorrency_cookbook.synchronization.data_access_lock;

/**
 * Created by Yang on 2015/2/2.
 */
public class Reader implements Runnable {
    private PriceInfo priceInfo;
    public Reader(PriceInfo priceInfo){
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i =0 ;i<10;i++){
            System.out.printf("%s 价格 1 是 %f \n", Thread.currentThread().getName(),priceInfo.getPrice1());
            System.out.printf("%s 价格 2 是 %f \n", Thread.currentThread().getName(),priceInfo.getPrice2());
        }
    }
}
