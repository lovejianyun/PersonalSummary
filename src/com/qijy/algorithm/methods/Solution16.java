package com.qijy.algorithm.methods;

import java.util.Arrays;

/*
 * @ Description   :  合并两个有序数组
 * @ Author        :  qijy
 * @ CreateDate    :  2021/2/4 10:27
 */
public class Solution16 {
    public static void main(String[] args) {
        int [] nums1 = new int[12];
        nums1[0] = 1;
        nums1[1] = 3;
        nums1[2] = 6;
        nums1[3] = 7;
        int [] nums2 = new int[]{2,4,4,5,8,9,10,11};
        merge(nums1,4,nums2,8);
        System.out.println(Arrays.toString(nums1));
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
//       System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
