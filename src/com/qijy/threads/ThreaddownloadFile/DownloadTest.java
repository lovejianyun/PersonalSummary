package com.qijy.threads.ThreaddownloadFile;

import java.io.IOException;
import java.util.Date;

/*
 * @ Description   :  主线程测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/2 17:10
 */
public class DownloadTest {
    public static void main(String[] args) {
        Date startDate = new Date();
        DownloadFileWithThreadPool pool = new DownloadFileWithThreadPool();
        try {
            String filepath = "F:/ideaworkspace/PersonalSummary"+"/"+"files"+"/"+"meinv.jpg";
            //pool.getFileWithThreadPool("http://llt-test.oss-cn-shenzhen.aliyuncs.com//file/snapshot/2020/5/27/425548747180773376.zip", filepath, 2);

            pool.getFileWithThreadPool("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604590360347&di=d4c7fd588ca7e0df711caebd9fe1ed6a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-25%2F59f0247b848e0.jpg", filepath, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new Date().getTime() - startDate.getTime());
    }
}
