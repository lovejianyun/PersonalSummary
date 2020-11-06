package com.qijy.algorithm.methods;

import java.util.HashMap;
import java.util.Stack;

/*
 * @ Description   :  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true

 * @ Author        :  qijy
 * @ CreateDate    :  2020/7/14 14:55
 */
public class Solution6 {
    HashMap<Character,Character> map = null;
    public Solution6() {
        map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }

    public boolean isValid(String s) {
        int length = s.length();
        if(length%2 != 0){
            return false;
        }
        if(s.contains("()")||s.contains("[]")||s.contains("{}")){
            return isValid(s.replace("()","").replace("[]","").replace("{}",""));
        }else{
            return "".equals(s);
        }

    }
    /*
     * @ Description   :  栈解决方案
     * @ Author        :  qijy
     * @ CreateDate    :  2020/7/14 15:41
     */
    public boolean isValid2(String s){
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <length ; i++) {
            if(map.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }else{
                if(!stack.isEmpty()){
                    Character pop = stack.pop();
                    if(s.charAt(i)!=map.get(pop)){
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        System.out.println(solution6.isValid2("{[]}"));
    }
}
