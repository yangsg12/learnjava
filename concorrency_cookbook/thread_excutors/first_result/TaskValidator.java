package concorrency_cookbook.thread_excutors.first_result;

import java.util.concurrent.Callable;

/**
 * Created by Yang on 2015/2/13.
 */
public class TaskValidator implements Callable<String> {
    private UserValidator userValidator;
    private String user;
    private String password;

    public TaskValidator(UserValidator userValidator, String user, String password) {
        this.userValidator = userValidator;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if (!userValidator.validator(user,password)) {
            System.out.printf("%s : 用户没有找到啊!\n", userValidator.getName());
            throw new Exception("找不到呢!");
        }
        System.out.printf(" %s : 用户已找到!!\n",userValidator.getName());
        return userValidator.getName();

    }
}
