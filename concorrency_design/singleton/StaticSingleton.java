package concorrency_design.singleton;

/**
 * Created by Yang on 2017/7/17.
 */
public class StaticSingleton {
    private StaticSingleton() {
        System.out.println("static singleton using inner class");
    }
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }

    // no lock
}
