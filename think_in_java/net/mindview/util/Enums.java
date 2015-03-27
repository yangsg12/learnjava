//: net/mindview/util/Enums.java
package think_in_java.net.mindview.util;
import java.util.*;

public class Enums {
  private static Random rand = new Random(47);
  // <T extends Enum<T>>  表示 T 是一个 enum 实例.
  public static <T extends Enum<T>> T random(Class<T> ec) {
    return random(ec.getEnumConstants());
  }
  public static <T> T random(T[] values) {
    return values[rand.nextInt(values.length)];
  }
} ///:~
