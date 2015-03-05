package head_first;

/**
 * Created by Yang on 2015/2/12.
 */
public class MiniDuck {
    public static void main(String[] args) {
        Duck duck = new Duck() {
            @Override
            public void display() {
                System.out.println(" new duck!!");
            }
        };

        duck.performFly();
        duck.performQuack();
    }
}
