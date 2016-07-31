package concorrency_practise;

/**
 * Created by Yang on 2016/7/31.
 */
public class MutablePoint {
    private int x;
    private int y;

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
    public MutablePoint(){
        x= 0;
        y = 0;
    }




}
