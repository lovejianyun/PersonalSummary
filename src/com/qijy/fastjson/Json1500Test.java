package com.qijy.fastjson;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringEscapeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/*
 * @ Description   :
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/28 11:21
 */
public class Json1500Test {
    public static void main(String[] args) {
        String json = "{\"USERID\":\"TJ308-C111\",\"JTYPE\":\"1\",\"LINKNUM\":\"0\",\"IP\":\"192.169.1.183\",\"STATUS\":\"1\",\"INTM\":\"2020-05-29 09:23:23.270\",\"OUTTM\":\"2020-05-29 09:23:23.270\",\"LASTVST\":\"2020-05-29 09:23:23.270\",\"OUTCNT\":\"2\",\"FEEFLAG\":\"1\",\"FEE\":\"0\",\"GLBFEE\":\"0\",\"SUCCRATE\":\"40\",\"MTSD\":\"123456\",\"MTNOSD\":\"100000\",\"MTMEMNOSD\":\"10000\",\"MTFILENOSD\":\"90000\",\"MTVFYNOSD\":\"3000\",\"MTLVL0NOSD\":\"2500\",\"MTSPD\":\"1000\",\"MTMINDLY\":\"1\",\"MTMAXDLY\":\"10000\",\"MTAVGDLY\":\"5000\",\"MO\":\"0\",\"MORV\":\"0\",\"MONORV\":\"0\",\"MOMEMNORV\":\"90\",\"MOFILENORV\":\"0\",\"MODBNORV\":\"0\",\"MOSPD\":\"0\",\"RPT\":\"1000000\",\"OPTRPT\":\"1000000\",\"OPTDTRPT\":\"1000000\",\"RPTRV\":\"990000\",\"OPTRPTRV\":\"990000\",\"OPTRPTDTRV\":\"990000\",\"OPTRPTDTRVSUCC\":\"990000\",\"RPTDTRVFAIL\":\"0\",\"OPTRPTDTRVFAIL\":\"0\",\"RPTNORV\":\"100000\",\"RPTMEMNORV\":\"100000\",\"RPTFILENORV\":\"100000\",\"RPTDBNORV\":\"100000\",\"RPTSPD\":\"5000\",\"MTLVLNOSD\":\"1/11/22/33/44/55/66/77/88/99\",\"RPTDTRV\":\"990000\",\"DTRPT\":\"1000000\",\"MT\":\"200000\",\"RPTDTRVSUCC\":\"990000\"}";
        long start = System.currentTimeMillis();
        test0001(json);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
    }

    private static void test0002(String json){
        JSON.parseObject(json,UserInfo.class);
    }

    private static void test0001(String json) {
        String s2 = StringEscapeUtils.unescapeJava(json);
        String substring = s2.substring(1, json.length() - 1);
        String s1 = substring.replaceAll("\"", "");
        String[] split = s1.split(",");
        UserInfo userInfo = new UserInfo();
        for (String s : split) {
            String[] split1 = s.split(":");
            String field = split1[0].toLowerCase();
            String value = "";
            for (int i=1;i<split1.length;i++) {
                value += split1[i]+":";
            }
            String substring1 = value.substring(0, value.length() - 1);
            setValue(field,substring1,userInfo);
        }
//        System.out.println(userInfo.getUserid());
//        System.out.println(userInfo.getFee());
//        System.out.println(userInfo.getDtrpt());
//        System.out.println(userInfo.getFeeflag());
//        System.out.println(userInfo.getGlbfee());
//        System.out.println(userInfo.getIntm());
    }

    private static <T> void setValue(String field,String value, T t) {
        try {
            Class<?> clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field1 : fields) {
                field1.setAccessible(true);
                if(field1.getName().equalsIgnoreCase(field)){
                    Type genericType = field1.getGenericType();
                    if("int".equals(genericType.getTypeName())){
                        field1.set(t,Integer.valueOf(value));
                    }else if("java.lang.String".equals(genericType.getTypeName())){
                        field1.set(t,value);
                    }
                    field1.setAccessible(false);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
