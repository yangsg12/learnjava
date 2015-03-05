package concorrency_cookbook.thread_excutors.first_result;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/13.
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public boolean validator(String name, String password) {
        Random random = new Random();
        long duration = (long) (Math.random() * 10);
        System.out.printf(" validator %s : %d 秒\n", name, duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return random.nextBoolean();
    }

}
