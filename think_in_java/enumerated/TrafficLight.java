package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */
enum Signal{
    GREEN, YELLOW,RED
}
public class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        switch (color) {
            case RED: color= Signal.GREEN;
                break;
            case GREEN: color = Signal.YELLOW;
                break;
            case YELLOW: color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "红绿灯是 "+color;
    }

    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 10; i++) {
            System.out.println(light);
            light.change();
        }
    }
}
