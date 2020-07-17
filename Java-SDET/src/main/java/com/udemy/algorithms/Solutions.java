package com.udemy.algorithms;

public class Solutions {

    public static int[] runningSum(int[] nums) {
        int[] b = new int[nums.length];
        for (int i = 1; i<= nums.length; i++){
            for (int j=0; j<i; j++){
                b[i-1]=b[i-1]+ nums[j];
            }
        }
        return b;
    }

    public static int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j]){
                    count++;
                }
            }
        }
        return count;
    }
}
