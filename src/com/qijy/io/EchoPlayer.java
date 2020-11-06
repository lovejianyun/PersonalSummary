package com.qijy.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoPlayer {
    public static String echo(String echo){
        return echo;
    }

    public static void talk() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = null;
        while((result =br.readLine())!= null){
            System.out.println(echo(result));
            if(result.equals("bye")){
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        EchoPlayer echoPlayer = new EchoPlayer();
        echoPlayer.talk();
    }
}
