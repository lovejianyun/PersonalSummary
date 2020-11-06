package com.qijy.sockers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class StrSocketClient extends BaseSocket {

    private Integer port;

    private String host;

    private Socket socket;

    public StrSocketClient(Integer port, String host) throws IOException {
        this.port = port;
        this.host = host;
        socket = new Socket(host,port);
    }

    public void talk() throws IOException {
        try {
            // 读取服务端响应数据
            BufferedReader reader = super.getReader(socket);
            // 写数据到服务端
            PrintStream writer = super.getWriter(socket);
            // 键盘输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while((str = bufferedReader.readLine())!= null){
                writer.println(str);
                System.out.println(reader.readLine());
                if("bye".equals(str)){
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null){
                 socket.close();
            }
        }
    }
}
