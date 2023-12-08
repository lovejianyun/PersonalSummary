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
//        test04();

        test06();
    }


    public static void test06(){
        Map<String,String> map = new HashMap<>();

        String str = "{\n" +
                "\t\"realIP\": \"111.76.168.62\",\n" +
                "\t\"wifiAble\": true,\n" +
                "\t\"cpuUsage\": \"to 2490\",\n" +
                "\t\"country\": \"zh_CN\",\n" +
                "\t\"appVersion\": \"4.2.7\",\n" +
                "\t\"btMac\": \"\",\n" +
                "\t\"channel\": \"AppStore\",\n" +
                "\t\"moduleName\": \"iPhone 11\",\n" +
                "\t\"hasJailbroken\": false,\n" +
                "\t\"language\": \"zh-Hans-CN\",\n" +
                "\t\"mcc\": \"460\",\n" +
                "\t\"type\": \"iOS\",\n" +
                "\t\"uuid\": \"C42E5F76-B600-4D82-9122-7EBE2E6CC1F4\",\n" +
                "\t\"resolution\": \"1792 x 828\",\n" +
                "\t\"deviceName\": \"温凯文的iPhone\",\n" +
                "\t\"deviceId\": \"I4RXAloZAQzIfmanPXB1692847046656\",\n" +
                "\t\"operator\": 1,\n" +
                "\t\"sdUseSize\": \"117.05G\",\n" +
                "\t\"province\": \"江西省\",\n" +
                "\t\"appId\": \"com.fujfu\",\n" +
                "\t\"currentSignal\": \"WIFI\",\n" +
                "\t\"idfv\": \"493ED08C-030B-4C9A-80FF-72D17643CAF5\",\n" +
                "\t\"phoneLocation\": \"Home按钮在下\",\n" +
                "\t\"radius\": \"10.000000\",\n" +
                "\t\"longitude\": \"114.909816\",\n" +
                "\t\"sdFreeSize\": \"2.10G\",\n" +
                "\t\"hasSimulator\": false,\n" +
                "\t\"deviceType\": 1,\n" +
                "\t\"buildVersion\": \"15\",\n" +
                "\t\"wifiIntensity\": \"NONE\",\n" +
                "\t\"ip\": \"111.76.168.62\",\n" +
                "\t\"wifiSsid\": \"乌纳康养发馆\",\n" +
                "\t\"idfa\": \"\",\n" +
                "\t\"ipAddress\": \"10.1.110.55\",\n" +
                "\t\"fbdId\": \"64e6ccb3fdcef22242f6d00c\",\n" +
                "\t\"cpu\": \"ARMv8-A\",\n" +
                "\t\"timeZone\": \"Local Time Zone (Asia/Shanghai (GMT+8) offset 28800)\",\n" +
                "\t\"hasBroken\": false,\n" +
                "\t\"sysCarrier\": \"中国移动\",\n" +
                "\t\"version\": \"4.2.7\",\n" +
                "\t\"identityOcrTime\": 1692847268,\n" +
                "\t\"iActiveNetType\": 10,\n" +
                "\t\"phoneNumber\": \"\",\n" +
                "\t\"district\": \"渝水区\",\n" +
                "\t\"openUdid\": \"cb779f347cf911c5c855735e9df0309c3d8c43de\",\n" +
                "\t\"hasDebug\": false,\n" +
                "\t\"sdAllSize\": \"119.15G\",\n" +
                "\t\"rebootTime\": \"2023-08-10 22:28:30\",\n" +
                "\t\"deviceBrand\": \"apple\",\n" +
                "\t\"gateWayWiFi\": \"192.168.0.1\",\n" +
                "\t\"fcuuid\": \"bf57bf0a8c2740e2ac0c71dc0e6da996\",\n" +
                "\t\"altitude\": \"81.449020\",\n" +
                "\t\"iNetworkType\": 13,\n" +
                "\t\"hasCharging\": false,\n" +
                "\t\"city\": \"新余市\",\n" +
                "\t\"bssid\": \"a4:1a:3a:37:ee:bf\",\n" +
                "\t\"latitude\": \"27.829618\",\n" +
                "\t\"imsi\": \"46002\",\n" +
                "\t\"ssid\": \"乌纳康养发馆\",\n" +
                "\t\"cpuInfo\": \"6**\",\n" +
                "\t\"mac\": \"\",\n" +
                "\t\"headPhonesAttached\": false,\n" +
                "\t\"manufacturer\": \"apple\",\n" +
                "\t\"deviceNum\": \"I4RXAloZAQzIfmanPXB1692847046656\",\n" +
                "\t\"osVersion\": \"14.6\",\n" +
                "\t\"street\": \"赣西大道\",\n" +
                "\t\"osType\": \"iOS\",\n" +
                "\t\"currency\": \"¥\",\n" +
                "\t\"networkType\": \"WIFI\",\n" +
                "\t\"isSimulator\": \"0\",\n" +
                "\t\"prescreeningId\": \"64e6ccb4b85c955cf49ed58e\",\n" +
                "\t\"batteryLevel\": \"0.96\",\n" +
                "\t\"orientation\": \"Home按钮在下\",\n" +
                "\t\"phoneType\": \"iOS\",\n" +
                "\t\"product\": \"apple\",\n" +
                "\t\"address\": \"中国江西省新余市渝水区赣西大道\",\n" +
                "\t\"mnc\": \"02\",\n" +
                "\t\"density\": \"326\",\n" +
                "\t\"os\": 1,\n" +
                "\t\"appName\": \"富宝袋\",\n" +
                "\t\"isoCountryCode\": \"cn\",\n" +
                "\t\"IPhoneType\": 1,\n" +
                "\t\"hasSupportTouchId\": false,\n" +
                "\t\"carrier\": \"中国移动\",\n" +
                "\t\"hasSupportFaceID\": true,\n" +
                "\t\"brightness\": \"0.64\",\n" +
                "\t\"wlanMac\": \"\",\n" +
                "\t\"netmaskWiFi\": \"255.255.255.0\",\n" +
                "\t\"time\": \"1692862265562\",\n" +
                "\t\"memSize\": \"3858.00M\"\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(str);
        String s = jsonObject.toJSONString();
        map.put("str",s);
        System.out.println(JSONObject.toJSONString(map));

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
