package concorrency_practise.server;

import java.util.concurrent.Executor;

/**
 * Created by Yang on 2016/8/5.
 */
public class WithInThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
