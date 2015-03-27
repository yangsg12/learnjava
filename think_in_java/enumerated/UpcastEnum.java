package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */
enum Search{
    HITHER,YON
}
public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals =Search.values();
        Enum e = Search.HITHER;
        // e.values  no value() in Enum
        // getEnumConstants()  是 Class 的方法.
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }
    }
}
