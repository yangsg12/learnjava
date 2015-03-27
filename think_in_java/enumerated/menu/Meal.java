package think_in_java.enumerated.menu;

/**
 * Created by Yang on 2015/3/27.
 */
public class Meal {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("-------------");
        }
    }
}
