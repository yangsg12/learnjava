package think_in_java.enumerated;

import think_in_java.net.mindview.util.Enums;

/**
 * Created by Yang on 2015/3/27.
 */
enum Activity{
    SITTING,LYING,STANDING,HOPPING,RUNNING,DODGING,JUMPING,FLYING
}
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Enums.random(Activity.class)+" ");
        }
    }
}
