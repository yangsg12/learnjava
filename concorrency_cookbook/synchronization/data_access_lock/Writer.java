package concorrency_cookbook.synchronization.data_access_lock;

/**
 * Created by Yang on 2015/2/2.
 */
public class Writer implements Runnable {
    private PriceInfo priceInfo;
    public Writer(PriceInfo priceInfo){
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i= 0 ;i<10;i++) {
            System.out.printf("要改价格了-----\n");
            priceInfo.setPrices((Math.random()*10),(Math.random()*20));
            System.out.printf("价格改好了######\n");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
