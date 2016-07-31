package concorrency_practise;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yang on 2016/7/31.
 */
public class PersonSet {
    private final Set<Person> mySet = new HashSet<Person>();
    public synchronized void addPerson(Person p){
        mySet.add(p);

    }
    public synchronized boolean cotainsPeson(Person p){
        return mySet.contains(p);
    }

}
