package com.java;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Yang
 * Date: 13-12-15
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */
public class Bool {
    public static void main(String[] args) {
        Random rand = new Random(47);
        int i = rand.nextInt(100);
        int j = rand.nextInt(100);
        System.out.println("i = "+i);
        System.out.println("j = "+j);
    }
}
