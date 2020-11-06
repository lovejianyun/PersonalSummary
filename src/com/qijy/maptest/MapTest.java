package com.qijy.maptest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","qijy");
        map.put("xxxx","tangting");
        map.remove("xxxx");
        printMap(map);
    }

    public static void printMap(Map<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }



}
