package com.qijy.algorithm.methods;
/*
 * @ Description   :  判断字符串a是不是字符串b的字串
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/28 8:56
 */
public class Solution12 {
    public boolean isSubStr(String a,String b){
        int alen = a.length();
        int blen = b.length();
        int i = 0;
        int j = 0;
        while(i<alen && j<blen){
            if(a.charAt(i) == b.charAt(j)){
                i++;
            }
            j++;
        }
        return i==alen;
    }

    public static void main(String[] args) {
        String a = "abc";

        String b = "adbefc";

        Solution12 solution12 = new Solution12();
        System.out.println(solution12.isSubStr(a, b));
    }

}
