package concorrency_design.interfaceMethod;

/**
 * Created by Yang on 2017/7/20.
 */
public class Mule implements IHorse, IDonkey,IAnimal {
    @Override
    public void eat() {
        System.out.println("mule eat...");
    }

    @Override
    public void run() {
        IHorse.super.run();/**/
        //run();
    }

    public static void main(String[] args) {
        Mule m = new Mule();
        m.run();
        m.breath();
    }
}
