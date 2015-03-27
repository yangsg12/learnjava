package think_in_java.enumerated;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Yang on 2015/3/27.
 */

interface Command{ void action();}
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
        em.put(AlarmPoints.KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("kitchen fire !!!");
            }
        });

        em.put(AlarmPoints.BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("bathroom 有人在洗澡");
            }
        });

        for (Map.Entry<AlarmPoints, Command> entry : em.entrySet()) {
            System.out.print(entry.getKey()+" : ");
            entry.getValue().action();
        }

    }
}
