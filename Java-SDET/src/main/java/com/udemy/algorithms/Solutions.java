package com.udemy.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    //    Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
    //    Return the array in the form [x1,y1,x2,y2,...,xn,yn].
    public static int[] shuffle(int[] nums, int n) {
        int[] nums1 = Arrays.copyOfRange(nums,0, n);
        int[] nums2 = Arrays.copyOfRange(nums, n, nums.length);
        ArrayList<Integer> numss = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            numss.add(nums1[i]);
            numss.add(nums2[i]);
        }
        nums = numss.stream().mapToInt(i->i).toArray();
        return nums;
    }

//    Given the array candies and the integer extraCandies, where candies[i] represents the number
//    of candies that the ith kid has.
//    For each kid check if there is a way to distribute extraCandies among the kids such
//    that he or she can have the greatest number of candies among them.
//    Notice that multiple kids can have the greatest number of candies.
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int greatest = 0;
        for (int candy : candies) {
            if (candy > greatest) {
                greatest = candy;
            }
        }
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= greatest);
        }
        return result;
    }

    //Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even,
    // you have to divide it by 2, otherwise, you have to subtract 1 from it.
    public static int numberOfSteps(int num) {
        int count=0;
        while (num !=0){
            if (num % 2 == 0){
                num = num /2;
            } else {
                num = num -1;
            }
            count++;
        }
        return count;
    }

    //You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
    //Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
    //The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
    // so "a" is considered a different type of stone from "A".
    public static int numJewelsInStones(String J, String S) {
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            count = count + S.length() - S.replace(Character.toString(J.charAt(i)), "").length();
        }
        return count;
    }

    //Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it.
    // That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int num : nums) {
                if (nums[i] > num) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }
}
