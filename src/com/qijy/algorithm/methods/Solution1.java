package com.qijy.algorithm.methods;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Description   :  判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
进阶:

你能不将整数转为字符串来解决这个问题吗？

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/10 17:32
 */
public class Solution1 {
    public boolean isPalindrome(int x) {
        int newX;
        int count = 0;
        if(x<0){
            return false;
        }
        if(x==reverse(x)){
            return true;
        }
        return false;
    }

    public int reverse(int x) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        while((x)!=0){
            int i = x % 10;
            list.add(i);
            x = x/10;
        }
        int j = list.size()-1;
        for(int k= 0;k<list.size();k++){
            sum  += (int) (list.get(k).intValue() *(Math.pow(10,j)));
            j--;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution1 Solution1 = new Solution1();
        System.out.println(Solution1.isPalindrome(121));
    }
    
}
