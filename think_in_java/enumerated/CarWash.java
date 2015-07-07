package think_in_java.enumerated;

import book_manage.vo.ValueObject;

import java.util.EnumSet;

/**
 * Created by Yang on 2015/3/30.
 */
public class CarWash {
    public enum Cycle{
        UNDERBODY{
            void action() {
                System.out.println("Spraying the under body");
            }
        },

        WHELLWASH{
            void action(){
                System.out.println("washing the wheels");
            }
        },

        PREWASH{
            void action(){
                System.out.println("loosening the dirt");
            }
        };

        abstract void action();
    }

    EnumSet<Cycle> cycles = EnumSet.of(Cycle.UNDERBODY, Cycle.PREWASH);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar(){
        for (Cycle cycle : cycles) {
            cycle.action();
        }
    }

    @Override
    public String toString() {
        return cycles.toString();
    }

    public static void main(String[] args) {
        CarWash wash = new CarWash();
        System.out.println(wash);
        wash.washCar();
        wash.add(Cycle.PREWASH);
        wash.add(Cycle.UNDERBODY);
        System.out.println(wash);
        wash.washCar();
    }
}
