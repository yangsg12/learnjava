package concorrency_cookbook.synchronization.conditions;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang on 2015/2/2.
 */
public class EventStorage {   //notify(), wait(),notifyAll()
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    public synchronized void set(){
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            storage.add(new Date());
            System.out.printf("Set : %d ",storage.size());
            notifyAll();

        }
    }

    public synchronized void get(){
        while (storage.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf(" Set %d  %s", storage.size(),((LinkedList<?>)storage).poll());
            notifyAll();
        }
    }
}
