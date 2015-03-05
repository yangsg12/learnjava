package com.java.collecitons;

import java.util.*;

/**
 * Created by Yang on 2015/3/2.
 */
public class CollectionAlgorithms {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 100));
        }

        Collections.sort(list);
        System.out.println("排了序: " + list);
        int testNumber = 10;
        int index = Collections.binarySearch(list, testNumber);
        if (index >=0){
            System.out.println("数字 : "+testNumber+"找到了在 index "+index);
        }else {
            System.out.println(testNumber+"没找到啊");
        }

        System.out.println("Max "+Collections.max(list));
        System.out.println("Min " + Collections.min(list));
        System.out.println("frequency " + Collections.frequency(list,testNumber));

        Set<Integer> sortedList = new HashSet<Integer>();
        sortedList.addAll(list);
        System.out.println("不重复的元素个数 " + sortedList.size());
        list.clear();
        list.addAll(sortedList);
        Collections.shuffle(list);

        List<Integer> topTenList = list.subList(0, 10);
        Collections.sort(topTenList);
        System.out.println("Top 10 :" + topTenList);
    }
}
