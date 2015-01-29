package com.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yang on 2015/1/27.
 */
public class DateExample2 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateStringToParse ="1-27-2015";
        try {
            Date date = sdf.parse(dateStringToParse);
            System.out.println(date.getTime());
            System.out.println(date.toString());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
