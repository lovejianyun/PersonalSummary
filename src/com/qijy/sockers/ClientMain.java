package com.qijy.sockers;

import java.io.IOException;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        StrSocketClient strSocketClient = new StrSocketClient(8021,"localhost");
        strSocketClient.talk();
    }
}
