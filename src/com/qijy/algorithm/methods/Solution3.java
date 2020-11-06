package com.qijy.algorithm.methods;
/*
 * @ Description   :  给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。

注意：整数序列中的每一项将表示为一个字符串。

「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：

1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1

描述前一项，这个数是 1 即 “一个 1 ”，记作 11

描述前一项，这个数是 11 即 “两个 1 ” ，记作 21

描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211

描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221

 

示例 1:

输入: 1
输出: "1"
解释：这是一个基本样例。
示例 2:

输入: 4
输出: "1211"
解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/13 14:42
 */
public class Solution3 {
    public String countAndSay1(int n) {
        StringBuilder s = new StringBuilder();
        int p1 = 0;
        int cur = 1;
        if ( n == 1 )
            return "1";
        String str = countAndSay1(n - 1);
        for ( cur = 1; cur < str.length(); cur++ ) {
            if ( str.charAt(p1) != str.charAt(cur) ) {// 如果碰到当前字符与前面紧邻的字符不等则更新此次结果
                int count = cur - p1;
                s.append(count).append(str.charAt(p1));
                p1 = cur;
            }
        }
        if ( p1 != cur ){// 防止最后一段数相等，如果不等说明p1到cur-1这段字符串是相等的
            int count = cur - p1;
            s.append(count).append(str.charAt(p1));
        }
        return s.toString();
    }

    public String countAndSay2(int n){
        String[] dp = new String[n] ;
        dp[0] = "1" ;
        StringBuilder sb = null ;
        for(int i = 1 ; i < n ; i++){
            int count = 1 ;
            sb = new StringBuilder() ;
            char[] ss = dp[i-1].toCharArray() ;
            int len = ss.length ;
            for(int j = 0 ; j < len - 1 ; j++){
                if(ss[j] == ss[j+1]){
                    count++ ;
                    continue ;
                }
                sb.append(String.valueOf(count)).append(ss[j]) ;
                count = 1 ;
            }
            sb.append(String.valueOf(count)).append(ss[len-1]) ;
            dp[i] = sb.toString() ;
        }
        return dp[n-1] ;

    }

    public String countAndSay3(){
        return "";
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        System.out.println(solution3.countAndSay2(5));
    }
}
