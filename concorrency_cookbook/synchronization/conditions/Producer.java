package concorrency_cookbook.synchronization.conditions;

/**
 * Created by Yang on 2015/2/2.
 */
public class Producer implements Runnable {
    private EventStorage storage;
    public Producer(EventStorage storage){
        this.storage = storage;
    }
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            storage.set();
        }
    }
}
