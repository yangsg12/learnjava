package think_in_java.enumerated.menu;

import think_in_java.net.mindview.util.Enums;

/**
 * Created by Yang on 2015/3/27.
 */
public enum Meal2 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class);

    private Food[] values;

    private Meal2(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    interface Food{
        enum Appetizer implements Food{
            SALAD,SOUP,SPRING_ROLLS;
        }

        enum MainCourse implements Food{
            BURRITO,PAD_THAI;
        }
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Meal2 meal2 : Meal2.values()) {
                Food food = meal2.randomSelection();
                System.out.println(food);
            }

            System.out.println("------------ " +i);
        }
    }
}
