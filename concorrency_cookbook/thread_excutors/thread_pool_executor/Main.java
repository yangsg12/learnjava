package concorrency_cookbook.thread_excutors.thread_pool_executor;

/**
 * Created by Yang on 2015/2/12.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(" Task " + i);
            server.executorTask(task);
        }
        server.endServer();
    }
}
