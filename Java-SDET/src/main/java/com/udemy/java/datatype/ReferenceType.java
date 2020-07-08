package com.udemy.java.datatype;

import java.util.Arrays;

public class ReferenceType {
    public static void main(String[] args) {
              //abc123
        int[] arr = {1, 2, 3};

        predict(arr);

        totalSale(arr);

    }
                                //abc123
    private static void predict(int[] a){
        a = Arrays.copyOf(a, a.length);
        a[0]++;
        a[1]++;
        System.out.println("Prediction for the next month :: " + (a[0] + a[1] + a[2]));
    }
                                //abc123
    private static void totalSale(int[] a){
        //abc123
        System.out.println("Total Sale of this month :: " + (a[0] + a[1] + a[2]));
    }

}
