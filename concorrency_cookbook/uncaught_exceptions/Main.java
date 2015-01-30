package concorrency_cookbook.uncaught_exceptions;

/**
 * Created by Yang on 2015/1/30.
 */
public class Main {
    public static void main(String[] args) {
        MyThreadGroup myThreadGroup = new MyThreadGroup("MyThreadGroup");
        Task task = new Task();
        for (int i =0; i<2;i++){
            Thread t = new Thread(myThreadGroup,task);
            t.start();
        }
    }
}
