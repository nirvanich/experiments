package com.udemy.java;

import java.util.Arrays;

import static com.udemy.java.reusableMethods.Utilities.sortArray;

public class TestMain {
    public static void main(String[] args) {

       int[] a = {7,1,2,3,4,5,6,8};
        System.out.println(Arrays.toString(a));
        sortArray(a);
    }




}
