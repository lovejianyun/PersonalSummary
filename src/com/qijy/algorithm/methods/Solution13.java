package com.qijy.algorithm.methods;

import java.util.Arrays;
import java.util.logging.Level;

/*
 * @ Description   :  给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

 

示例 1：

输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]
示例 2：

输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]
 

提示：

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A 已按非递减顺序排序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/28 10:45
 */
public class Solution13 {
    public int[] sortedSquares(int[] A) {
        int result [] = new int[A.length];
        for (int i = 0; i<A.length; i++){
            result[i] = A[i]*A[i];
        }
        Arrays.sort(result);
        return result;
    }
    public int[] sortedSquares1(int [] A){
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j-1;

        int[] ans = new int[N];
        int t = 0;

        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                ans[t++] = A[i] * A[i];
                i--;
            } else {
                ans[t++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            ans[t++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            ans[t++] = A[j] * A[j];
            j++;
        }

        return ans;

    }

    public static void main(String[] args) {
        int [] A = new int []{-4,-1,0,3,10};
        Solution13 solution13 = new Solution13();
        System.out.println(Arrays.toString(solution13.sortedSquares1(A)));
    }

}
