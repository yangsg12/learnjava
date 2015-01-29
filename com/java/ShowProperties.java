package com.java;

/**
 * Created with IntelliJ IDEA.
 * User: Yang
 * Date: 13-12-15
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class ShowProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
//        System.out.println(System.getProperties("user.name"));
//        System.out.println(System.getProperties("user.name"));
    }
}
