package com.qijy.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * @ Description   :  多线程复制文件
 * @ Author        :  qijy
 * @ CreateDate    :  2020/11/6 9:04
 */
public class ThreadsCopyFile implements Runnable {
    // 源文件
    private File srcFile;
    // 复制文件
    private File desFile;
    // 文件读取起始位置
    private long start;
    // 文件读取结束位置
    private long end;

    public ThreadsCopyFile(File srcFile, File desFile, long start, long end) {
        this.srcFile = srcFile;
        this.desFile = desFile;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        // 文件复制
        fileCopy();
    }

    private void fileCopy(){
        RandomAccessFile readFile = null;
        RandomAccessFile writeFile = null;
        if(checkParams()){
            int len = 0;
            try {
                readFile = new RandomAccessFile(srcFile,"r");
                writeFile = new RandomAccessFile(desFile,"rw");
                byte [] bytes = new byte[1024*8];

                readFile.seek(start);
                writeFile.seek(start);
                while ((len=readFile.read(bytes))!=-1){
                    writeFile.write(bytes,0,len);
                    // 说明已经读取到end位置
                    if(writeFile.getFilePointer()>=end){
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(readFile!=null){
                    try {
                        readFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(writeFile!=null){
                    try {
                        writeFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "处理从" + "start:" + start + "到end:" + end);

            }
        }
    }

    /*
     * @ Description   :  文件检验,参数校验
     * @ Author        :  qijy
     * @ CreateDate    :  2020/11/6 9:25
     */
    private boolean checkParams(){
        boolean flag = true;

        if(null == srcFile || !srcFile.exists()){
            flag = false;
        }
        if(start<0){
            flag = false;
        }
        if(end<0){
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();

        File srcFile = new File("F:\\ideaworkspace\\PersonalSummary\\files\\meinv.jpg");
        File desFile = new File("F:\\ideaworkspace\\PersonalSummary\\files\\buty.jpg");
        int threadcount = 16 ;
        ThreadsCopyFile [] threadsCopyFiles = new ThreadsCopyFile[threadcount];

        Thread [] threads = new Thread[threadcount];
        long length = srcFile.length();
        long avg = length/threadcount/(1024*8)*1024*8;
        for (int i=0;i<threadcount;i++){
            long start = i*avg;
            long end = (i+1)*avg;
            if(i == threadcount -1){
                end = length;
            }
            threadsCopyFiles[i] = new ThreadsCopyFile(srcFile,desFile,start,end);
            threads [i] = new Thread(threadsCopyFiles[i],"文件复制线程:"+i);
        }
        for (Thread thread : threads) {
                thread.start();
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时:"+(e-s));
    }

}
