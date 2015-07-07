package think_in_java.generic;

/**
 * Created by Yang on 2015/4/30.
 */

class HoldItem<T>{
    T item;
    HoldItem(T item){
        this.item = item;
    }

    T getItem() {
        return item;
    }
}



public class InheritBounds {
}
