package think_in_java.enumerated;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Yang on 2015/3/27.
 */
enum Explore{HERE,THERE}
public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("-----analyzing " + enumClass + " ----");
        System.out.println("interfaces:");
        for (Type type : enumClass.getGenericInterfaces()) {
            System.out.println(type);
        }
        System.out.println("base: " + enumClass.getSuperclass());
        System.out.println("Methods :");
        Set<String> methods = new TreeSet<String>();
        for (Method method : enumClass.getMethods()) {
            methods.add(method.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploredMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " + exploredMethods.containsAll(enumMethods));
        System.out.println("Explore.removeAll(Enum) :" + exploredMethods.removeAll(enumMethods));
        System.out.println(exploredMethods);

    }
}
