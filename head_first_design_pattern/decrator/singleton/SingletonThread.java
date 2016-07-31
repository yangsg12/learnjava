package head_first_design_pattern.decrator.singleton;

/**
 * Created by Yang on 2016/4/1.
 */

public class SingletonThread {
    private static volatile SingletonThread singletonThread = null;

    private SingletonThread(){}

    public static SingletonThread getSingletonThread(){
        synchronized (SingletonThread.class){
            if (singletonThread == null) {
                singletonThread = new SingletonThread();
            }
            return singletonThread;
        }
    }
    // 同步多， 效率低

}
