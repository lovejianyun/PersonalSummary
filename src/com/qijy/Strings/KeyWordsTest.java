package com.qijy.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyWordsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("习近平");
        list.add("垃圾");
        String text = "是个什么人学习成习绩平时近况";
        Integer xx = 6;
        String keywordsIndex = getKeywordsIndex(text, list, 6);
        System.out.println(keywordsIndex);


    }
    //查询关键字下表索引
    public static String getKeywordsIndex(String text,List<String> list ,Integer xx){
        StringBuffer sb = new StringBuffer();
        // 短信长度
        int textLen = text.length();
        for (int i = 0; i < list.size(); i++) {
            // 获取关键字长度
            int keywordsLen = list.get(i).length();
            int offset = 0;
            boolean contains = text.contains(list.get(i));
            // 完全匹配
            while (contains){
                // 完全匹配的下表索引
                int allMatchIndex = text.indexOf(list.get(i),offset);
                if(allMatchIndex != -1){
                    offset += allMatchIndex + keywordsLen;
                    for (int j = 0 ;j<=keywordsLen-1;j++){
                        sb.append(allMatchIndex+j).append(",");
                    }
                }else{
                    break;
                }

            }
            // 不完全匹配的情况
            if(!contains){
                // 找下标存储到存储容器
                List<List<Integer>> listsData = new ArrayList<>();
                for (int k=0 ;k<keywordsLen;k++){
                    // 拆分关键字
                    char c = list.get(i).charAt(k);
                    int offset2 = 0;
                    List<Integer> list1 = new ArrayList<>();
                    while((offset2 = text.indexOf(c,offset2)) != -1 ){
                        list1.add(offset2);
                        offset2 += 1;
                    }
                    listsData.add(list1);
                }
                // 找数据
                List<List<Integer>> result = new ArrayList<>();
                for (int m = listsData.size()-1;m>0;m--){
                    List<Integer> integers = listsData.get(m);
                    for (int a=0;a<integers.size();a++){
                        Integer integer = integers.get(a);
                        List<Integer> integers1 = listsData.get(m - 1);
                        for (int b=0;b<integers1.size();b++){
                            // 两关键字之间的差值
                            int x = (integer-integers1.get(b));

                        }
                    }

                }
            }
        }
        return sb.toString();
    }
}
