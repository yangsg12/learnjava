package concorrency_design.singleton;

/**
 * Created by Yang on 2017/7/17.
 */
public class Singleton {
    private Singleton() {   // 1. private
        System.out.println("Singleton is create");
    }
    private static Singleton instance = new Singleton(); // 2. private static instance

    public static Singleton getInstance() {
        return instance;
    }
}
