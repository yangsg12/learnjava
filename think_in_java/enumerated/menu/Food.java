package think_in_java.enumerated.menu;

/**
 * Created by Yang on 2015/3/27.
 */
public interface Food {
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }

    enum MainCourse implements Food{
        LASAGNE,BURRITO,PAD_THAI,
        LENTILS,HUMMOUS,VINDALOO;
    }
}
