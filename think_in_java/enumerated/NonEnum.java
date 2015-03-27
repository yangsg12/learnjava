package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */
public class NonEnum {
    public static void main(String[] args) {
        Class <Integer> integerClass = Integer.class;
        try {
            for (Object en : integerClass.getEnumConstants()) {
                System.out.println(en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
