package com.qijy.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonTest {
    public static void main(String[] args) {
//        test01();

//        test02();
//        test03();
        test04();
    }

    public static void test01(){
        AAA a = new AAA();
        a.setAge("18");
        a.setGrade("100");
        a.setName("qijy");
        System.out.println(JSON.toJSONString(a));
        String xx = JSON.toJSONString(a);
        AAA aaa = JSONObject.parseObject(xx, AAA.class);
        System.out.println(aaa.getAge());
        System.out.println(aaa.getGrade());
        System.out.println(aaa.getName());
    }

    public static void test02(){
        List<AAA> aaa = new ArrayList<>();
        AAA aa = new AAA();
        aa.setAge("19");
        aa.setGrade("100");
        aa.setName("qijy");
        aaa.add(aa);
        AAA aaa1 = new AAA();
        aaa1.setAge("19");
        aaa1.setGrade("80");
        aaa1.setName("sb");
        aaa.add(aaa1);
        String xxx = JSONArray.toJSONString(aaa);
        System.out.println(xxx);
        List<AAA> aaas = JSONArray.parseArray(xxx, AAA.class);
        System.out.println("---------------------------");
        for (AAA aaax : aaas){
            System.out.println(aaax.getName());
            System.out.println(aaax.getGrade());
            System.out.println(aaax.getAge());
        }
    }

    public static void test03(){
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("NAME","xxxx");
        map.put("AGE","18");
        System.out.println(JSON.toJSONString(map));

        list.add(map);
        Map<String,String> map1 = new HashMap<>();
        map1.put("NAME","oooo");
        map1.put("AGE","20");
        list.add(map1);
        System.out.println(JSONArray.toJSONString(list));

        String listxx = JSONArray.toJSONString(list);

        List<Map> maps = JSONArray.parseArray(listxx, Map.class);

        for (Map map2 : maps) {
            System.out.println(map2.get("NAME"));
        }

    }

    public static void test04(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","xxxx");
        jsonObject.put("age",18);
        jsonObject.put("greade",80);
        System.out.println(jsonObject.toJSONString());

        System.out.println("-------------------");
        jsonObject.toJSONString();
    }

    public static void test05(){


    }

}
