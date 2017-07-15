package ctolib.comparaotr;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang on 2017/2/21.
 */
public class Person {
    private final String name;
    private final int  age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public int ageDif (Person other){
        return age - other.age;
    }

    public String toString(){
        return String.format(" %s - %d ", name, age);
    }



    public static void main(String[] args) {
        final List<Person> personList = Arrays.asList(
                new Person("Jhon", 20),
                new Person("Tom", 20),
                new Person("Jack", 22),
                new Person("Nel", 23),
                new Person("Bruce", 24)

        );

//        List<Person> ascendingAge = personList.stream()
//               // .sorted((person1,person2) -> person1.ageDif(person2))
//                //.collect(toList());

//        printPeople("sorted by ascending age ", ascendingAge);

        System.out.println("hello");
    }

    public static void printPeople(String message, List<Person> people) {
        System.out.println(message);
        //people.forEach(System.out::println);
    }

}
