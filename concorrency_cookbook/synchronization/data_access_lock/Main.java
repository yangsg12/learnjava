package concorrency_cookbook.synchronization.data_access_lock;

/**
 * Created by Yang on 2015/2/2.
 */
public class Main {
    public static void main(String[] args) {
        PriceInfo priceInfo = new PriceInfo();
        Reader reader[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0 ; i<5;i++) {
            reader[i] = new Reader(priceInfo);
            threadsReader[i] = new Thread(reader[i]);
        }

        Writer writer = new Writer(priceInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0 ;i<5;i++){
            threadsReader[i].start();
        }

        threadWriter.start();
    }
}
