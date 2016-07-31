package concorrency_practise;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yang on 2016/7/30.
 */
public final class ThreeStooges {
    private final Set<String > stooges = new HashSet<String>();
    public ThreeStooges(){
        stooges.add("Meo");
        stooges.add("Rio");
        stooges.add("May");

    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }
}
