package j1200;

import java.util.Scanner;

/**
 * Created by Yang on 2014/12/23.
 */
public class LeftMove {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入吧");
        long number = scanner.nextLong();
        System.out.println("您输入的是"+number);
        System.out.println("左移1位 相当于乘以2 的n次方"+(number<<1));
        System.out.println("左移2位 相当于乘以2 的n次方"+(number<<2));
        System.out.println("左移3位 相当于乘以2 的n次方"+(number<<3));
    }
}
