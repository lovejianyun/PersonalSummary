package com.qijy.algorithm.methods;
/*
 * @ Description   :  给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/9 11:50
 */
public class Solution {
    private int n;
    int i = 1;
    public Solution(int n) {
        this.n = n;
    }

    public int reverse(){
        boolean isNum = false;
        String result = "";
        if(n<-Math.pow(2,31) || n>(Math.pow(2,31)-1)){
            return Integer.valueOf("0");
        }
        if (n<0){
           n = -n;
           isNum = true;
        }
        String nStr = String.valueOf(n);
        if(nStr.contains("-")){
            nStr = nStr.substring(1,nStr.length());
        }
        for (int i = nStr.length()-1; i >=0 ; i--) {
            result +=nStr.charAt(i);
        }
        result = String2int(result);
        if(isNum){
            result = "-" + result;
        }
        try {
            return Integer.valueOf(result);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String String2int(String str){
        try {
            Long integer = Long.valueOf(str);
            return integer.toString();
        } catch (NumberFormatException e) {
            str = str.substring(i,str.length());
            i++;
            String2int(str);
        }
        return "0";
    }
    //
    public static void main(String[] args) {
        Solution Solution = new Solution(-2147483648);
        System.out.println(Solution.reverse());
    }
}
