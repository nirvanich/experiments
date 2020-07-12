package com.udemy.java;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {

        //int[] a = {10, 1, 3, 2, 4, 5, 6, 8, 9, 7};
       int[] a = {4, 3, 2, 1, 5, 6};
        String array = Arrays.toString(a);
        System.out.println(array);
        sortArray(a);
    }

    private static void sortArray(int[] a) {
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
