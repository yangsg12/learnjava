package j1200;

import java.util.Scanner;

/**
 * Created by Yang on 2014/12/23.
 */
public class PairCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入啊");
        long number = scanner.nextLong();
        String check =(number%2==0)?"是偶数":"奇数啊";
        System.out.println(check);
    }
}
