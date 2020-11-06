package com.qijy.threads.ThreaddownloadFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * @ Description   :  线程
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/2 17:00
 */
public class DownloadWithRange implements Runnable {
    private String urlLocation;

    private String filePath;

    private long start;

    private long end;

    DownloadWithRange(String urlLocation, String filePath, long start, long end) {
        this.urlLocation = urlLocation;
        this.filePath = filePath;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection conn = getHttp();
            // 这个很重要  要不然图片会错乱
            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);

            File file = new File(filePath);
            RandomAccessFile out = null;
            if (file != null) {
                out = new RandomAccessFile(file, "rw");
            }
            out.seek(start);
            InputStream in = conn.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) >= 0) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public HttpURLConnection getHttp() throws IOException {
        URL url = null;
        if (urlLocation != null) {
            url = new URL(urlLocation);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");


        return conn;
    }

}
