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

    //Given an integer number n, return the difference between the product of its digits and the sum of its digits.
    public static int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        while (n > 0){
            int left = n % 10;
            n = n /10;
            product = left*product;
            sum = sum + left;
        }
        return product-sum;
    }
    //Write a program that outputs the string representation of numbers from 1 to n.
    //But for multiples of three it should output “Fizz” instead of the number and for the multiples
    // of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < n +1; i++) {
            if ((i % 3 == 0) & (i % 5 == 0)){
                list.add("FizzBuzz");
            } else if (i % 3 == 0){
                list.add("Fizz");
            } else if (i % 5 == 0){
                list.add("Buzz");
            } else
                list.add(""+i);
        }
        return list;
    }

    //Given an array nums of integers, return how many of them contain an even number of digits.
    public static int findNumbers(int[] nums) {
        int result = 0;
        for (int num: nums){
            int count = String.valueOf(num).length();
            if (count % 2 == 0){
                result++;
            }
        }
        return result;
    }

    //Given a sorted array nums, remove the duplicates in-place such that each element appear only once
    // and return the new length.
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j< nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    //Say you have an array prices for which the ith element is the price of a given stock on day i.
    //Design an algorithm to find the maximum profit. You may complete as many transactions as you like
    // (i.e., buy one and sell one share of the stock multiple times).
    //Note: You may not engage in multiple transactions at the same time
    // (i.e., you must sell the stock before you buy again).
    public static int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i-1] < prices[i]){
                sum = sum + prices[i] - prices[i-1];
            }
        }
        return sum;
    }

    //Given a string s and an integer array indices of the same length.
    //The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
    //Return the shuffled string.
    public static String restoreString(String s, int[] indices) {
        String result = "";
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                if (indices[j]==i) {
                    result = result + s.charAt(j);
                }
            }
        }
        return result;
    }

    //Given two arrays of integers nums and index. Your task is to create target array under the following rules:
    //Initially target array is empty.
    //From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
    //Repeat the previous step until there are no elements to read in nums and index.
    //Return the target array.
    public static int[] createTargetArray(int[] nums, int[] index) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            result.add(index[i], nums[i]);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    //Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference
    // between any two consecutive elements is the same.
    //Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
    public static boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        boolean flag = true;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i+1] - arr[i] != diff) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //Given an array, rotate the array to the right by k steps, where k is non-negative.
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    //Given an array of integers, find if the array contains any duplicates.
    //Your function should return true if any value appears at least twice in the array,
    // and it should return false if every element is distinct.
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

    //Balanced strings are those who have equal quantity of 'L' and 'R' characters.
    //Given a balanced string s split it in the maximum amount of balanced strings.
    //Return the maximum amount of splitted balanced strings.
    public static int balancedStringSplit(String s) {
        int balancedCount = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == 'L'){
                count++;
            } else if (current == 'R'){
                count--;
            }
            if (count == 0){
                balancedCount++;
            }
        }
        return balancedCount;
    }
}
