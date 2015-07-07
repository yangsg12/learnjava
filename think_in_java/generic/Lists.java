package think_in_java.generic;

import java.util.*;

/**
 * Created by Yang on 2015/4/2.
 */
public class Lists {
    public static <T> List<T> toList(T... arr) {
        List<T> list = new ArrayList<T>();
        for (T t : arr) {
            list.add(t);
        }
        return list;
    }

    public static <T> void copy(List<Object> dst, List<? super T> src) {
        for (int i = 0; i < src.size(); i++) {
            dst.set(i, src.get(i));
        }
    }

 }

