package head_first_design_pattern.decrator.singleton.thread_and_effective;

/**
 * Created by Yang on 2016/4/1.
 */
public class Singleton {
    private static volatile Singleton singleton = null;

    private Singleton(){}

    public static Singleton getSingleton(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /*双重检查锁”，顾名思义，就是在getSingleton()方法中，
    进行两次null检查。看似多此一举，但实际上却极大提升了并发度，
    进而提升了性能。为什么可以提高并发度呢？就像上文说的，
    在单例中new的情况非常少，绝大多数都是可以并行的读操作。
    因此在加锁前多进行一次null检查就可以减少绝大多数的加锁操作，
    执行效率提高的目的也就达到了。*/
}
