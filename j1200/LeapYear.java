package j1200;

import java.util.Scanner;

/**
 * Created by Yang on 2014/12/23.
 */
public class LeapYear {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入一个年份");
        long year = scan.nextLong();
        if (year%4==0 && year %100 != 0 || year%400 ==0){
            System.out.println("是润年啊");
        }else {
            System.out.println(year+"不是闰年！！！");
        }
    }
}
