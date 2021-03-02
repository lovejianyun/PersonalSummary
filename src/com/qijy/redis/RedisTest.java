package com.qijy.redis;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Description   :  redis 测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/30 15:26
 */
public class RedisTest {
    public static void main(String[] args) throws Exception{
//        test0001();
        test0002();
    }

    private static void test0001() throws Exception {
        CommonCacheDao commonCacheDao = new CommonCacheDao();
        long start = System.currentTimeMillis();
        for(int i=0 ;i< 100000; i++){
            commonCacheDao.setStr("qijyasdfadf"+i,"xxxxxasdfasdf"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }

    private static void test0002 () throws Exception{
        CommonCacheDao commonCacheDao = new CommonCacheDao();
        List<String> keys = new ArrayList<>();
        keys.add("qijy521521");
        List<String> params = new ArrayList<>();
        params.add("5");
        params.add("60");
        commonCacheDao.eval(buildLuaScript(),keys,params);
    }

    /**
     * 限流脚本
     */
    private static String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
