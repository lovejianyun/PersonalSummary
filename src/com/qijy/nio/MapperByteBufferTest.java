package com.qijy.nio;

import sun.misc.Cleaner;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

/*
 * @ Description   :  
 * @ Author        :  qijy
 * @ CreateDate    :  2020/10/10 15:58
 */
public class MapperByteBufferTest {
    public static void main(String[] args) throws Exception{
        String path = "C:\\Users\\Administrator\\Desktop\\xxxxxxxxx\\1500_1602301609774_1_1_81.xml";
        test01(path);
    }

    private static void test01(String path) {
        RandomAccessFile randomAccessFile = null;
        FileChannel channel = null;
        MappedByteBuffer map = null;
        try {
            randomAccessFile = new RandomAccessFile(path,"rw");
            channel = randomAccessFile.getChannel();
            long length = randomAccessFile.length();
            byte [] buf = new byte[(int) length];
            map = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i=0;i<(int) length;i++){
                buf[i] = map.get(i);
            }
            // 将mappedbytebuffer转成字符串
            String s = new String(buf,"utf-8");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 清空缓存数据
            clean(map);
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(randomAccessFile != null){
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
     * @ Description   :  清空mappedByteBuffer中的数据
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/15 11:23
     */
    private static void clean(final MappedByteBuffer buffer) {
        if(null == buffer)
            return;
        AccessController.doPrivileged((PrivilegedAction) () -> {
            try {
                Method getCleanerMethod = buffer.getClass().getMethod(
                        "cleaner", new Class[0]);
                getCleanerMethod.setAccessible(true);
                Cleaner cleaner = (Cleaner) getCleanerMethod
                        .invoke(buffer, new Object[0]);
                cleaner.clean();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

    }

    private static void test02(String path) throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"rw");

    }

}
