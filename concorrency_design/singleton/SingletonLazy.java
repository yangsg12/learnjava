package concorrency_design.singleton;

/**
 * Created by Yang on 2017/7/17.
 */
public class SingletonLazy {
    private SingletonLazy() {
        System.out.println("Singleton lazy");
    }
    private static SingletonLazy instance = null;

    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
