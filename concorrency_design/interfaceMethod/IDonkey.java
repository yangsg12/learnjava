package concorrency_design.interfaceMethod;

/**
 * Created by Yang on 2017/7/20.
 */
public interface IDonkey {
    void eat();

    default void run() {
        System.out.println("donkey run");
    }
}
