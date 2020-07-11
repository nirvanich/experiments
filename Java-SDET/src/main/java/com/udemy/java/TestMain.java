package com.udemy.java;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {

        int[] a = {10, 9, 7, 6, 4, 5, 2, 8, 3, 1};
        String array = Arrays.toString(a);
        System.out.println(array);
        sortArray(a);
    }

    private static void sortArray(int[] a) {
        int n = a.length;
        for (int j = 0; j < n-1; j++) {
            for (int i = 0; i < n - 1; i++) {
                int temp;
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }


}
