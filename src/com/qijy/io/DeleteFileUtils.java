package com.qijy.io;

import java.io.File;

public class DeleteFileUtils {
    /*
     * @ Description   :  批量删除文件
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/12 8:45
     */
    public static void batchedeletefile(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isFile()){
                file1.delete();
            }else{
                batchedeletefile(file1.getPath());
            }
        }
        file.delete();
    }
    /*
     * @ Description   :  删除文件下的所有文件
     * @ Author        :  qijy
     * @ CreateDate    :  2020/10/12 9:12
     */
    public static void batchedeletefile(File file){
        File[] files = file.listFiles();
        for (File file1 : files) {
            if(file1.isFile()){
                file1.delete();
            }else{
                batchedeletefile(file1);
            }
        }
        // 删除空文件夹
        file.delete();
    }


    public static void main(String[] args) {
        String path = "F:\\filedelete";
        batchedeletefile(new File(path));
    }
}
