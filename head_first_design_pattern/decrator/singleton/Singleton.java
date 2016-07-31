package head_first_design_pattern.decrator.singleton;

/**
 * Created by Yang on 2016/4/1.
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){}
    public  Singleton getSingleton(){
        return singleton;
    }
}
