package concorrency_cookbook;

/**
 * Created by Yang on 2015/1/29.
 */
public class ExceptionHandlerMain {
    public  void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();

    }
}
