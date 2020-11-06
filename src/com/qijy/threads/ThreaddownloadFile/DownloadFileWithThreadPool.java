package com.qijy.threads.ThreaddownloadFile;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @ Description   :  线程池
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/2 17:05
 */
public class DownloadFileWithThreadPool {
    public void getFileWithThreadPool(String urlLocation, String filePath, int poolLength) throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        long len = getContentLength(urlLocation);
        String dir = filePath.substring(0, filePath.lastIndexOf("/"));
        createFiles(dir);
        System.out.println(len);
        for (int i = 0; i < poolLength; i++) {
            long start = i * len / poolLength;
            long end = (i + 1) * len / poolLength;
            if (i == poolLength - 1) {
                end = len;
            }
            System.out.println(start+"---------------"+end);
            DownloadWithRange download = new DownloadWithRange(urlLocation, filePath, start, end);
            threadPool.execute(download);
        }
        threadPool.shutdown();
    }

    public static long getContentLength(String urlLocation) throws IOException {
        URL url = null;
        if (urlLocation != null) {
            url = new URL(urlLocation);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        long len = conn.getContentLength();
        return len;
    }
    /*
     * @ Description   :  创建文件夹
     * @ Author        :  qijy
     * @ CreateDate    :  2020/6/2 17:37
     */
    public void createFiles(String dir){
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
