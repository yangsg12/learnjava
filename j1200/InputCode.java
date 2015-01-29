package j1200;

import java.util.Scanner;

/**
 * Created by Yang on 2014/12/23.
 */
public class InputCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入点啥吧");
        String line = scanner.nextLine();
        System.out.println("你输入了 "+line.length()+"啊");
    }
}
