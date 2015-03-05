package concorrency_cookbook.thread_excutors.first_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yang on 2015/2/13.
 */
public class Main {
    public static void main(String[] args) {
        String username = "test";
        String password = "test";

        UserValidator ldapUserValidator = new UserValidator("LDAP");
        UserValidator dbapUserValidator = new UserValidator("Data Base");

        TaskValidator ldapTask = new TaskValidator(ldapUserValidator, username, password);
        TaskValidator dbTask = new TaskValidator(dbapUserValidator, username, password);

        List<TaskValidator> list = new ArrayList<TaskValidator>();
        list.add(ldapTask);
        list.add(dbTask);

        ExecutorService executor = (ExecutorService) (Executors.newCachedThreadPool());
        String result;
        try {
            result = executor.invokeAny(list);
            System.out.printf("Main : result %s \n",result);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("Main: 执行结束!\n");
    }
}
