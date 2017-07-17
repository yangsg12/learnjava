package concorrency_design.producer_consumer;

import think_in_java.net.mindview.util.CountingGenerator;

/**
 * Created by Yang on 2017/7/17.
 */
public final class PCData {
    private final int intData;
    public PCData(int d){
        intData = d;
    }

    public PCData(String d ){
        intData = Integer.valueOf(d);
    }

    public int getData(){
        return intData;
    }

    @Override
    public String toString() {
        return "data: "+ intData;
    }
}
