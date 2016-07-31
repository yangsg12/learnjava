package concorrency_practise;

/**
 * Created by Yang on 2016/7/31.
 */
public final class Counter {
    private long value = 0;
    public synchronized long getValue(){
        return value;
    }

    public synchronized long increment(){
        if (value == Long.MAX_VALUE){
            throw new IllegalStateException("Counter overflow");

        }
        return value++;
    }
}
