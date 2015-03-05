package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Yang on 2015/3/2.
 */
public class ThreadScheduler {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 10));
        }

        PriorityQueue<Integer> threadQueue = new PriorityQueue<Integer>();
        threadQueue.addAll(list);

        System.out.println("Waiting threads...");

        for (Integer thread: threadQueue){
            System.out.print(thread+ " ,");
        }
        System.out.println("\nDeploying threads...");

        while (!threadQueue.isEmpty()){
            System.out.print(threadQueue.remove()+ ",");
        }
    }
}
