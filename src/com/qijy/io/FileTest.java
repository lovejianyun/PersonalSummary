package com.qijy.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
        getVersion();
    }

    private static String getVersion() {
        String path = "";
        String result = "";
        String projectpath = System.getProperty("user.dir");
        BufferedReader br = null;
        try {
            path = projectpath+"/application.properties";
            br = new BufferedReader(new FileReader(new File(path)));
            result = br.readLine();
        } catch (Exception e) {
        }finally {
            try {
                br.close();
            } catch (IOException e) {

            }
        }
        return result;
    }

}
