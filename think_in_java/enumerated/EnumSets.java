package think_in_java.enumerated;

import java.util.EnumSet;

/**
 * Created by Yang on 2015/3/27.
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.add(AlarmPoints.BATHROOM);
        System.out.println(points);
        points = EnumSet.allOf(AlarmPoints.class);
        System.out.println(points);
    }
}
