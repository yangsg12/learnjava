package j1200;

import java.util.Scanner;

/**
 * Created by Yang on 2014/12/23.
 */
public class Example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入吧");
        String password = scanner.nextLine();
        char [] array = password.toCharArray();
        for (int i =0;i < array.length;i++){
            array[i] = (char) (array[i]^20000);
        }
        System.out.println("加密后");
        System.out.println(new String(array));
    }
}
