package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/11.
 */

public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){ return canceled;}
}
