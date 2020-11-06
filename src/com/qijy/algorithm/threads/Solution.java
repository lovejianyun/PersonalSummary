package com.qijy.algorithm.threads;

import java.util.Arrays;

/*
 * @ Description   :
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/6 14:19
 */
public class Solution {
    public static void main(String[] args) {
        int [] nums = {2, 7, 11, 15,16,17} ;int target = 31;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }


    public static int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        for(int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i]+nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("没有两个数之和的解");

    }
}
