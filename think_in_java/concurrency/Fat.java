package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/26.
 */
public class Fat {
    private volatile double d ;
    private static int counter = 0;
    private final int id = counter++;

    // 构造 起来很耗时!!!
    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E)/(double)i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id : " + id;
    }
}
