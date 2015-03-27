package think_in_java.enumerated.menu;

import think_in_java.net.mindview.util.Enums;

/**
 * Created by Yang on 2015/3/27.
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class);

    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }


}
