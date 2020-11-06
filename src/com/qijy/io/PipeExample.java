package com.qijy.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeExample {
    public static void main(String[] args) throws Exception{
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream();
        new Thread(() -> {
            try {
                pipedOutputStream.connect(pipedInputStream);
                pipedOutputStream.write("qijy".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    pipedOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            try {
                int read = pipedInputStream.read();
                while(read != -1){
                    System.out.println((char) read);
                    read = pipedInputStream.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
}
