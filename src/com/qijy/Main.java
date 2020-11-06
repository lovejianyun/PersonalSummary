package com.qijy;
import com.qijy.kafkas.Consumers;
import com.qijy.kafkas.Producer;
import com.qijy.kafkas.config.ConfigLoad;

public class Main {

    public static void main(String[] args) {
        ConfigLoad configLoad = new ConfigLoad();
        configLoad.initConfig();
//        testProducer();
//        Consumers.getResult("monitor_storage","M_CHANNEL_CACHE");
        System.out.println(Consumers.getLag("monitor_storage","M_CHANNEL_CACHE"));
    }

    private static void testProducer() {
        String str = "INSERT INTO M_GATE_CMPP_BUF2008(EVTDATE,EVTTIME,GATEID,MTSD,MTNOSD,MORV,MOSD,MONOSD,RPTRV,RPTSD,RPTNOSD,MTNOSD0,MTNOSD1,MTNOSD2,MTNOSD3,MTNOSD4,MTNOSD5,MTNOSD6,MTNOSD7,MTNOSD8,MTNOSD9,EVTTM,STATUS,MTMEMNOSD,MTFILENOSD,MTVFYNOSD,MTLVL0NOSD,MOMEMNOSD,MOFILENOSD,MODBNOSD,RPTMEMNOSD,RPTFILENOSD,RPTDBNOSD,CREATETM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE()); -<>- 20200827 -<>- 61212 -<>- 4999 -<>- 73828 -<>- 0 -<>- 74 -<>- 66 -<>- 0 -<>- 73158 -<>- 73158 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- datatime:2020-08-27 17:00:12 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0";
        long s = System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            Producer.execMsgSend("M_CHANNEL_CACHE","M_CHANNEL_CACHE",str);
        }
        long e = System.currentTimeMillis();
        System.out.println("耗时:"+(e-s));
        Producer.close();
    }

    private static void testConsumer(){
        Consumers consumers = new Consumers("monitor_storage","M_CHANNEL_CACHE");
        consumers.getTopicPartitions();
    }
}