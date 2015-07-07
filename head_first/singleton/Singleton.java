package head_first.singleton;

/**
 * Created by Yang on 2015/4/2.
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton(){}
// 饿汉式  加载时 实例化
    public static Singleton getInstance() {
        return singleton;
    }
}

// 懒汉式  调用 实例方法 时,   实例化

