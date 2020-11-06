package com.qijy.sockers;

import java.io.*;
import java.net.Socket;

public class BaseSocket {
    /**
     * 从客户端获取数据
     * @param socket
     * @return
     * @throws IOException
     */
    public BufferedReader getReader(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader;
    }

    /**
     * 响应给客户端数据
     * @param socket
     * @return
     * @throws IOException
     */
    public PrintStream getWriter(Socket socket) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream,true);
        return printStream;
    }
}
