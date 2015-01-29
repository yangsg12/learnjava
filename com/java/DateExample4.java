package com.java;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Yang on 2015/1/27.
 */
public class DateExample4 {
    public static void main(String[] args) {
        Date date = new Date();

        DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat midDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat longDateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        System.out.println(shortDateFormat.format(date));
        System.out.println(midDateFormat.format(date));
        System.out.println(longDateFormat.format(date));

    }
}
