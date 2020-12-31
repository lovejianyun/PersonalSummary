package com.qijy.bisets;

import java.util.HashMap;
import java.util.Map;

public class ByteTest {
    public static void main(String[] args) {
        Map<String,Integer> ss = new HashMap<String,Integer>();
        String str = "xxxx";
        for(int i=0 ; i<10 ; i++){
            if(!ss.containsKey(str)){
                ss.put(str,1);
            }else{
                ss.put(str,ss.get(str)+i);
            }
        }
        System.out.println(ss.get(str));
    }
}
