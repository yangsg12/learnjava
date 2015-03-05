package head_first;

/**
 * Created by Yang on 2015/2/12.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.printf("quack 叫了.");
    }
}
