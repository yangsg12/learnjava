package concorrency_cookbook;

/**
 * Created by Yang on 2015/1/29.
 */
public class Main {
    public static void main(String[] args) {
        for (int i =0;i<10;i++){
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}
