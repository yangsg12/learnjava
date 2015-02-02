package concorrency_cookbook.synchronization.data_access_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Yang on 2015/2/2.
 */
public class PriceInfo {
    private double price1;
    private double price2;
    private ReadWriteLock readWriteLock;
    public PriceInfo(){
        price1 =  1.0;
        price2 = 2.0;
        readWriteLock = new ReentrantReadWriteLock();
    }

    public double getPrice1(){
        readWriteLock.readLock().lock();
        double value = price1;
        readWriteLock.readLock().unlock();
        return value;
    }

    public double getPrice2(){
        readWriteLock.readLock().lock();
        double value = price2;
        readWriteLock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1,double price2){
        readWriteLock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        readWriteLock.writeLock().unlock();
    }
}
