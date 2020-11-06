package com.qijy.algorithm.methods;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Description   :  编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/17 8:29
 */
public class Solution10 {
    public String longestCommonPrefix(String[] strs) {
        int length = strs.length;
        if (strs==null || strs.length==0){
            return "";
        }
        if(length==1){
            return strs[0];
        }
        String result = strs[0];
        for (int i=1;i<length;i++){
            int j=0;
            for (;j<result.length()&&j<strs[i].length();j++){
                if(result.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            result = result.substring(0,j);
            if(result.equals("")){
                return result;
            }
        }
        return result;
    }

    public String longestCommonPrefix1(String[] strs){
        if (strs==null || strs.length==0){
            return "";
        }
        String result = strs[0];
        Character[][] character = new Character[strs.length][];

        for (int i=0;i<strs.length;i++){
            for (int j=0;j<strs[i].length();j++){
               character[i][j] = strs[i].charAt(j);
            }
        }
        return "";

    }



    public static void main(String[] args) {
        String [] strs = new String[]{"flower","flow","flight"};
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.longestCommonPrefix1(strs));
    }
}
