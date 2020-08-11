package com.udemy.algorithms;

import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {

        boolean flag = containsDuplicate(new int[]{1,2,3,4});
        System.out.println(flag);
    }

    public static boolean containsDuplicate(int[] nums) {
        boolean flag = false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
                if (nums[i] == nums[i+1]){
                    flag = true;
                    break;
                }
            }
        return flag;
    }


}
