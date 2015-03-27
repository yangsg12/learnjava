package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */

import static think_in_java.enumerated.Spiciness.*;
public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Burrito is "+ degree;
    }

    public static void main(String[] args) {
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));

    }
}
