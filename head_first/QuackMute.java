package head_first;

/**
 * Created by Yang on 2015/2/12.
 */
public class QuackMute implements QuackBehavior {
    @Override
    public void quack() {
        System.out.printf("没有叫呢");
    }
}
