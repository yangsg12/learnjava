package concorrency_practise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yang on 2016/8/4.
 */
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());
    public boolean putIfAbsent(E e){
        // synchronized list
        synchronized (list){
            boolean absent = !list.contains(e);
            if (absent){
                list.add(e);
            }
            return absent;
        }
    }
}
