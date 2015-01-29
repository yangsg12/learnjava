package com.java;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yang on 2015/1/27.
 */
public class DateExample {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
        Date date = new Date();
        System.out.println(sdf.format(date));
    }
}
