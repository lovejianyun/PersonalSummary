package com.qijy.algorithm.methods;
/*
 * @ Description   :  实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：

输入: s = "leetcode"
输出: false
示例 2：

输入: s = "abc"
输出: true
限制：

0 <= len(s) <= 100
如果你不使用额外的数据结构，会很加分。

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/14 11:56
 */
public class Solution4 {
    public boolean isUnique(String astr) {
        int length = astr.length();
        for (int i = 0; i <length-1 ; i++) {
            for (int j = i+1; j <length ; j++) {
                if(astr.charAt(i) == astr.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution4 Solution4 = new Solution4();
        System.out.println(Solution4.isUnique("ltcode123456"));
    }
}
