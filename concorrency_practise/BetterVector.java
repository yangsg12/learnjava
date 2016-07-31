package concorrency_practise;

import java.util.Vector;

/**
 * Created by Yang on 2016/3/6.
 */
public class BetterVector<E> extends Vector<E>{
    public synchronized boolean putIfAbsent(E x){
        boolean absent = ! contains(x);
        if (absent){
            add(x);
        }
        return absent;
    }
}
