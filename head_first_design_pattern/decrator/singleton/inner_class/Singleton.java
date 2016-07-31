package head_first_design_pattern.decrator.singleton.inner_class;

/**
 * Created by Yang on 2016/4/1.
 */
public class Singleton {
    private static class Holder{
        private static Singleton singleton = new Singleton();
    }

    private Singleton(){}

    public static Singleton getSingleton(){
        return Holder.singleton;
    }
}
