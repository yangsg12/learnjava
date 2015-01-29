package com.java;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Yang on 2015/1/27.
 */
public class DateExample3 {
    public static void main(String[] args) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());

        System.out.println("System Date : "+ dateFormat.format(calendar.getTime()));

        calendar.set(GregorianCalendar.DAY_OF_WEEK,GregorianCalendar.FRIDAY);
        System.out.println("After set day of week to friday "+ dateFormat.format(calendar.getTime()));

        int friday13Count =0;
        while (friday13Count <=10){
            calendar.add(GregorianCalendar.DAY_OF_MONTH, 7);
            if (calendar.get(GregorianCalendar.DAY_OF_MONTH) == 13){
                friday13Count++;
                System.out.println(dateFormat.format(calendar.getTime()));
            }
        }
    }
}
