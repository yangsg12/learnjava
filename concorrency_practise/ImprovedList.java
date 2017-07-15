package concorrency_practise;

import java.util.List;

/**
 * Created by Yang on 2016/8/4.
 */
public abstract class ImprovedList<T> implements List<T> {
    private final List<T> list;

    public ImprovedList(List<T> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(T x) {
        boolean contains = list.contains(x);
        if (contains) {
            list.add(x);
        }
        return !contains;
    }

    @Override
    public synchronized void clear() {
        list.clear();
    }
}
