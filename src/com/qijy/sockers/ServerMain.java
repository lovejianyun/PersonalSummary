package com.qijy.sockers;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        Integer port = 8021;
        StrSocketServer strSocketServer = new StrSocketServer(port);
        strSocketServer.service();
    }
}
