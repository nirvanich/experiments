package com.udemy.java.reusableMethods;

import java.util.Arrays;

public class Utilities {
    public static void sortArray(int[] a) {
        int runTimes= 0;

        for (int j = 1; j < a.length; j++) {
            boolean sorted = true;
            for (int i = 0; i < a.length - j; i++) {
                runTimes++;
                if (a[i] > a[i + 1]) {
                    sorted = false;
                    int temp = a[i+1];
                    a[i+1] = a[i];
                    a[i] = temp;
                }
            }
            if (sorted){
                break;
            }

        }
        System.out.println(Arrays.toString(a));
        System.out.println(runTimes);
    }
}
