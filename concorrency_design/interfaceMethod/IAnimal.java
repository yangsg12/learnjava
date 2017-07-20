package concorrency_design.interfaceMethod;

/**
 * Created by Yang on 2017/7/20.
 */
public interface IAnimal {
    default void breath() {
        System.out.println("breath");
    }
}
