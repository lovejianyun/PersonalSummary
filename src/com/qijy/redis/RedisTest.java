package com.qijy.redis;
/*
 * @ Description   :  redis 测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/30 15:26
 */
public class RedisTest {
    public static void main(String[] args) throws Exception{
        CommonCacheDao commonCacheDao = new CommonCacheDao();
        long start = System.currentTimeMillis();
        for(int i=0 ;i< 100000; i++){
            commonCacheDao.setStr("qijyasdfadf"+i,"xxxxxasdfasdf"+i);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
    }
}
