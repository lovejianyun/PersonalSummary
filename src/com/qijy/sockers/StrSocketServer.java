package com.qijy.sockers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class StrSocketServer extends BaseSocket{
    private Integer port;
    private ServerSocket serverSocket;

    public StrSocketServer(Integer port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        System.out.println("服务端启动了!");
    }

    /**
     * 服务启动程序，将客户端程序打印，响应数据给服务端
     * @throws IOException
     */
    public void service() throws IOException{
        while (true){
            Socket socket = null;
            try {
                // 如果没有数据就阻塞
                socket = serverSocket.accept();
                if (socket != null){
                    BufferedReader reader = getReader(socket);
                    PrintStream writer = getWriter(socket);
                    String str = null;
                    while ((str = reader.readLine())!= null){
                        System.out.println(str);
                        writer.println(responseStr(str));
                        if("bye".equals(str)){
                            break;
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if (socket != null){
                    socket.close();
                }
            }
        }
    }

    public String responseStr(String str){
        return "Server:hello:"+str;
    }
}
