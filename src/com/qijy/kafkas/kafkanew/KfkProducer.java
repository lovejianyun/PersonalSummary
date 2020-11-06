package com.qijy.kafkas.kafkanew;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KfkProducer {
	
   public static void main(String[] args) {
	   
//	   KafkaSendThread KafkaSendThread1 = new KafkaSendThread("1120", 2085);
//	   KafkaSendThread1.start();
	   
	   /*KafkaSendThread KafkaSendThread1 = new KafkaSendThread("1118", 1085);
	   KafkaSendThread KafkaSendThread2 = new KafkaSendThread("1118", 1185);
	   KafkaSendThread KafkaSendThread3 = new KafkaSendThread("1118", 1285);
	   KafkaSendThread KafkaSendThread4 = new KafkaSendThread("1118", 1385);
	   KafkaSendThread KafkaSendThread5 = new KafkaSendThread("1118", 1485);
	   KafkaSendThread KafkaSendThread6 = new KafkaSendThread("1118", 1585);
	   KafkaSendThread KafkaSendThread7 = new KafkaSendThread("1118", 1685);
	   KafkaSendThread KafkaSendThread8 = new KafkaSendThread("1118", 1785);
	   KafkaSendThread KafkaSendThread9 = new KafkaSendThread("1118", 1885);
	   KafkaSendThread KafkaSendThread0 = new KafkaSendThread("1118", 1985);*/
	   
	   /*KafkaSendThread KafkaSendThread1 = new KafkaSendThread("1120", 2085);
	   KafkaSendThread KafkaSendThread2 = new KafkaSendThread("1120", 2185);
	   KafkaSendThread KafkaSendThread3 = new KafkaSendThread("1120", 2285);
	   KafkaSendThread KafkaSendThread4 = new KafkaSendThread("1120", 2385);
	   KafkaSendThread KafkaSendThread5 = new KafkaSendThread("1120", 2485);
	   KafkaSendThread KafkaSendThread6 = new KafkaSendThread("1120", 2585);
	   KafkaSendThread KafkaSendThread7 = new KafkaSendThread("1120", 2685);
	   KafkaSendThread KafkaSendThread8 = new KafkaSendThread("1120", 2785);
	   KafkaSendThread KafkaSendThread9 = new KafkaSendThread("1120", 2885);
	   KafkaSendThread KafkaSendThread0 = new KafkaSendThread("1120", 2985);*/
	   
	   /*KafkaSendThread KafkaSendThread10 = new KafkaSendThread("1120", 1085);
	   KafkaSendThread KafkaSendThread20 = new KafkaSendThread("1120", 2085);
	   KafkaSendThread KafkaSendThread30 = new KafkaSendThread("1120", 3085);
	   KafkaSendThread KafkaSendThread40 = new KafkaSendThread("1120", 485);
	   KafkaSendThread KafkaSendThread50 = new KafkaSendThread("1120", 5085);
	   KafkaSendThread KafkaSendThread60 = new KafkaSendThread("1120", 6085);
	   KafkaSendThread KafkaSendThread70 = new KafkaSendThread("1120", 7085);
	   KafkaSendThread KafkaSendThread80 = new KafkaSendThread("1120", 8085);
	   KafkaSendThread KafkaSendThread90 = new KafkaSendThread("1120", 9085);
	   KafkaSendThread KafkaSendThread00 = new KafkaSendThread("1120", 1285);*/
	   
	   
	   //KafkaSendThread1.start();
	   /*KafkaSendThread2.start();
	   KafkaSendThread3.start();
	   KafkaSendThread4.start();
	   KafkaSendThread5.start();
	   KafkaSendThread6.start();
	   KafkaSendThread7.start();
	   KafkaSendThread8.start();
	   KafkaSendThread9.start();
	   KafkaSendThread0.start();*/
	   
	   /*KafkaSendThread10.start();
	   KafkaSendThread20.start();
	   KafkaSendThread30.start();
	   KafkaSendThread40.start();
	   KafkaSendThread50.start();
	   KafkaSendThread60.start();
	   KafkaSendThread70.start();
	   KafkaSendThread80.start();
	   KafkaSendThread90.start();
	   KafkaSendThread00.start();*/
	   
	   
       /*Map<String, Object> props = new HashMap<String, Object>();
       
       Bootstrap_Servers = 192.169.2.202:9092,192.169.2.248:9092,192.169.2.249:9092
    	Zookeeper_Connect = 192.169.2.202:2181,192.169.2.248:2181,192.169.2.249:2181
       props.put("bootstrap.servers", "192.169.2.202:9092,192.169.2.248:9092,192.169.2.249:9092");
       
       //props.put("bootstrap.servers", "192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
       
       // key指定的反序列化方式
       props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
       // value指定的反序列化方式
       props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
       
       
        * 消息确认
        * 0：只要消息发送出去，不管数据有没有到Partition Leader上落到磁盘，直接认为消息发送成功。如果节点挂掉，会丢消息。因为不需要确认消息是否成功落盘，所以性能最高。
        * 1：消息发送出去，且Partition Leader接收到消息而且写入本地磁盘，就认为成功，不管其他的Follower有没有同步这条消息。如果Leader所在节点挂掉，会丢消息。因为只需要确认Leader成功，所以性能要好点。
        * -1或all：消息发送出去，且Partition Leader接收到消息而且写入本地磁盘，还必须要求ISR列表里跟Leader保持同步的那些Follower都要把消息同步过去，才能认为这条消息是写入成功。有两个副本以上的情况下不会丢消息，如果只有一个副本，则跟设置为1没区别。因为要确认所有副本，所以性能最低。
        
       props.put("acks", "all");
       
       // 如果请求失败，生产者会自动重试，指定是3次，如果启用重试，则会有重复消息的可能性
       props.put("retries", 3);
       // 每个分区未发送消息的缓存大小，默认16384Bytes，即16kb就会批量发送一次
       props.put("batch.size", 263840);
       // sender线程在检查batch.size是否ready时候，判断有没有过期的参数，默认大小是0ms。设置为1ms，即1ms就会批量发一次，不管batch.size是否满
       props.put("linger.ms", 1000);
       // 内存缓冲的大小的，默认值是32MB
       //props.put("buffer.memory", 33554432);
       // 开启 GZIP 压缩
       props.put("compression.type", "gzip");
       
       String topic = "1117";
       Integer key = 1685;
       String msgTemp = "<EVENT><EVTID>1500</EVTID><EVTTYPE>99</EVTTYPE><EVTTM>2019-12-08 00:00:00.259</EVTTM><VERSION>V4.5</VERSION><EVTCONT>[{ \"USERID\":\"JG0109\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:53\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"61265537\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8677\", \"MT\":\"106788\", \"MTSD\":\"94114\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"1075\", \"MORV\":\"1075\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"107153\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"106051\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"107153\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"106051\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"92667\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"13384\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0097\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:58:47\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"87184257\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9799\", \"MT\":\"9673\", \"MTSD\":\"9702\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"12\", \"OPTRPT\":\"9639\", \"DTRPT\":\"12\", \"OPTDTRPT\":\"9586\", \"RPTRV\":\"12\", \"OPTRPTRV\":\"9620\", \"RPTDTRV\":\"12\", \"OPTRPTDTRV\":\"9586\", \"RPTDTRVSUCC\":\"1\", \"OPTRPTDTRVSUCC\":\"9478\", \"RPTDTRVFAIL\":\"11\", \"OPTRPTDTRVFAIL\":\"108\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0096\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:39\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"80971442\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9915\", \"MT\":\"4613\", \"MTSD\":\"4624\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"13\", \"MORV\":\"13\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"6\", \"OPTRPT\":\"4609\", \"DTRPT\":\"5\", \"OPTDTRPT\":\"4591\", \"RPTRV\":\"5\", \"OPTRPTRV\":\"4604\", \"RPTDTRV\":\"5\", \"OPTRPTDTRV\":\"4591\", \"RPTDTRVSUCC\":\"2\", \"OPTRPTDTRVSUCC\":\"4572\", \"RPTDTRVFAIL\":\"3\", \"OPTRPTDTRVFAIL\":\"19\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0110\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:56\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"47872366\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9658\", \"MT\":\"87989\", \"MTSD\":\"86742\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"0\", \"MO\":\"9998\", \"MORV\":\"9997\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"25\", \"OPTRPT\":\"88134\", \"DTRPT\":\"24\", \"OPTDTRPT\":\"86655\", \"RPTRV\":\"25\", \"OPTRPTRV\":\"87439\", \"RPTDTRV\":\"24\", \"OPTRPTDTRV\":\"86655\", \"RPTDTRVSUCC\":\"8\", \"OPTRPTDTRVSUCC\":\"84979\", \"RPTDTRVFAIL\":\"16\", \"OPTRPTDTRVFAIL\":\"1676\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0100\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:44:14\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"78458193\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9377\", \"MT\":\"7679\", \"MTSD\":\"7229\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"32\", \"MORV\":\"32\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"7683\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"7651\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"7664\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"7651\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"7201\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"450\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0098\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:41:01\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"92614977\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9526\", \"MT\":\"1626\", \"MTSD\":\"1584\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"25\", \"MORV\":\"25\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"1618\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"1608\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"1617\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"1608\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"1549\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"59\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0112\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:35:01\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"90174184\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9677\", \"MT\":\"5182\", \"MTSD\":\"5105\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"1\", \"MTAVGDLY\":\"1\", \"MO\":\"1268\", \"MORV\":\"1268\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"1\", \"OPTRPT\":\"5174\", \"DTRPT\":\"1\", \"OPTDTRPT\":\"5110\", \"RPTRV\":\"1\", \"OPTRPTRV\":\"5138\", \"RPTDTRV\":\"1\", \"OPTRPTDTRV\":\"5110\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"5015\", \"RPTDTRVFAIL\":\"1\", \"OPTRPTDTRVFAIL\":\"95\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0133\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-25 21:42:34\", \"OUTTM\":\"2018-03-25 21:42:34\", \"LASTVST\":\"2019-12-07 23:19:43\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"99360000\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8974\", \"MT\":\"234\", \"MTSD\":\"234\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"203\", \"MTMAXDLY\":\"203\", \"MTAVGDLY\":\"203\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"226\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"216\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"223\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"216\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"210\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"6\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0210\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-10-30 01:57:17\", \"OUTTM\":\"2018-10-30 01:57:17\", \"LASTVST\":\"2019-12-07 23:48:42\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"18480075\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9463\", \"MT\":\"33836\", \"MTSD\":\"33418\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"196\", \"MTAVGDLY\":\"105\", \"MO\":\"66\", \"MORV\":\"66\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"34128\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"33155\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"34128\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"33155\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"32021\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"1134\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0175\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\"218.17.39.34:51172\", \"STATUS\":\"7\", \"INTM\":\"2019-07-11 11:58:53\", \"OUTTM\":\"2019-07-11 12:09:17\", \"LASTVST\":\"2019-12-08 00:00:00\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"4997170\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9271\", \"MT\":\"118648\", \"MTSD\":\"120466\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"1\", \"MO\":\"99\", \"MORV\":\"99\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"825\", \"OPTRPT\":\"117067\", \"DTRPT\":\"802\", \"OPTDTRPT\":\"114556\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"56\", \"OPTRPTDTRVSUCC\":\"109953\", \"RPTDTRVFAIL\":\"746\", \"OPTRPTDTRVFAIL\":\"4603\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0115\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:55\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"49934104\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8111\", \"MT\":\"42620\", \"MTSD\":\"36869\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"2415\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"12\", \"MORV\":\"12\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"43202\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"41560\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"42344\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"41560\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"34106\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"7454\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/2413/0\" },{ \"USERID\":\"JG0136\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-07-01 06:39:09\", \"OUTTM\":\"2018-07-01 06:39:09\", \"LASTVST\":\"2019-12-07 23:12:40\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"50009693\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8646\", \"MT\":\"1433\", \"MTSD\":\"1276\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"54\", \"MTMAXDLY\":\"80\", \"MTAVGDLY\":\"0\", \"MO\":\"11\", \"MORV\":\"11\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"5805\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"1414\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"5800\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"1414\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"1239\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"175\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" }]</EVTCONT></EVENT>";
       //msgTemp = "<EVENT><EVTID>1500</EVTID><EVTTYPE>99</EVTTYPE><EVTTM>2019-12-08 00:00:00.259</EVTTM><VERSION>V4.5</VERSION><EVTCONT>[{ \"USERID\":\"JG0109\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:53\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"61265537\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8677\", \"MT\":\"106788\", \"MTSD\":\"94114\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"1075\", \"MORV\":\"1075\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"107153\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"106051\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"107153\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"106051\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"92667\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"13384\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0097\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:58:47\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"87184257\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9799\", \"MT\":\"9673\", \"MTSD\":\"9702\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"12\", \"OPTRPT\":\"9639\", ";
       String msg = msgTemp;
       
       Producer<Integer, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<Integer, String>(props);
       
       long b = System.currentTimeMillis();
       
       int count = 100000;
       int index = 0;
       System.out.println("开始："+count);
       while(index < count) {
    	   index++;
    	   ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(topic, key, msg);
    	   //ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(topic, msg);
    	   KafkaSendCallback callback = new KafkaSendCallback();
    	   producer.send(record, callback);
       }
       System.out.println("结束："+index);
       long e = System.currentTimeMillis();
	   long t = e-b;
	   System.out.println("耗时："+(t));
       
       
       producer.close();*/
       KfkProducer kfkProducer = new KfkProducer();

       kfkProducer.send("M_CHANNEL_CACHE","M_CHANNEL_CACHE");

   }
   
   public void send(String topic, String key) {
       Map<String, Object> props = new HashMap<String, Object>();
       
       /*Bootstrap_Servers = 192.169.2.202:9092,192.169.2.248:9092,192.169.2.249:9092
    	Zookeeper_Connect = 192.169.2.202:2181,192.169.2.248:2181,192.169.2.249:2181*/
//       props.put("bootstrap.servers", "192.169.2.202:9092,192.169.2.248:9092,192.169.2.249:9092");
       
       props.put("bootstrap.servers", "192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
       props.put("zookeeper.connect","192.169.6.137:2181,192.169.6.25:2181,192.169.0.33:2181");
       
       // key指定的反序列化方式
       props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
       // value指定的反序列化方式
       props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
       
       /*
        * 消息确认
        * 0：只要消息发送出去，不管数据有没有到Partition Leader上落到磁盘，直接认为消息发送成功。如果节点挂掉，会丢消息。因为不需要确认消息是否成功落盘，所以性能最高。
        * 1：消息发送出去，且Partition Leader接收到消息而且写入本地磁盘，就认为成功，不管其他的Follower有没有同步这条消息。如果Leader所在节点挂掉，会丢消息。因为只需要确认Leader成功，所以性能要好点。
        * -1或all：消息发送出去，且Partition Leader接收到消息而且写入本地磁盘，还必须要求ISR列表里跟Leader保持同步的那些Follower都要把消息同步过去，才能认为这条消息是写入成功。有两个副本以上的情况下不会丢消息，如果只有一个副本，则跟设置为1没区别。因为要确认所有副本，所以性能最低。
        */
       props.put("acks", "all");
       
       // 如果请求失败，生产者会自动重试，指定是3次，如果启用重试，则会有重复消息的可能性
       props.put("retries", 3);
       // 失败重试间隔，毫秒
       props.put("retry.backoff.ms", 500);
       // 每个分区未发送消息的缓存大小，默认16384Bytes，即16kb就会批量发送一次
       props.put("batch.size", 563840);
       // sender线程在检查batch.size是否ready时候，判断有没有过期的参数，默认大小是0ms。设置为1ms，即1ms就会批量发一次，不管batch.size是否满
       props.put("linger.ms", 1000);
       // 内存缓冲的大小的，默认值是32MB
       props.put("buffer.memory", 93554432);
       // 开启 GZIP 压缩
       props.put("compression.type", "lz4");
       props.put("max.request.size", 10485760);
       
       String msgTemp = "INSERT INTO M_GATE_CMPP_BUF2008(EVTDATE,EVTTIME,GATEID,MTSD,MTNOSD,MORV,MOSD,MONOSD,RPTRV,RPTSD,RPTNOSD,MTNOSD0,MTNOSD1,MTNOSD2,MTNOSD3,MTNOSD4,MTNOSD5,MTNOSD6,MTNOSD7,MTNOSD8,MTNOSD9,EVTTM,STATUS,MTMEMNOSD,MTFILENOSD,MTVFYNOSD,MTLVL0NOSD,MOMEMNOSD,MOFILENOSD,MODBNOSD,RPTMEMNOSD,RPTFILENOSD,RPTDBNOSD,CREATETM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GETDATE()); -<>- 20200827 -<>- 61212 -<>- 4999 -<>- 73828 -<>- 0 -<>- 74 -<>- 66 -<>- 0 -<>- 73158 -<>- 73158 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- datatime:2020-08-27 17:00:12 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0 -<>- 0";
       //String msgTemp = "<EVENT><EVTID>1500</EVTID><EVTTYPE>99</EVTTYPE><EVTTM>2019-12-08 00:00:00.259</EVTTM><VERSION>V4.5</VERSION><EVTCONT>[{ \"USERID\":\"JG0109\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:53\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"61265537\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8677\", \"MT\":\"106788\", \"MTSD\":\"94114\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"1075\", \"MORV\":\"1075\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"107153\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"106051\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"107153\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"106051\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"92667\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"13384\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0097\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:58:47\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"87184257\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9799\", \"MT\":\"9673\", \"MTSD\":\"9702\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"12\", \"OPTRPT\":\"9639\", \"DTRPT\":\"12\", \"OPTDTRPT\":\"9586\", \"RPTRV\":\"12\", \"OPTRPTRV\":\"9620\", \"RPTDTRV\":\"12\", \"OPTRPTDTRV\":\"9586\", \"RPTDTRVSUCC\":\"1\", \"OPTRPTDTRVSUCC\":\"9478\", \"RPTDTRVFAIL\":\"11\", \"OPTRPTDTRVFAIL\":\"108\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0096\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:39\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"80971442\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9915\", \"MT\":\"4613\", \"MTSD\":\"4624\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"13\", \"MORV\":\"13\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"6\", \"OPTRPT\":\"4609\", \"DTRPT\":\"5\", \"OPTDTRPT\":\"4591\", \"RPTRV\":\"5\", \"OPTRPTRV\":\"4604\", \"RPTDTRV\":\"5\", \"OPTRPTDTRV\":\"4591\", \"RPTDTRVSUCC\":\"2\", \"OPTRPTDTRVSUCC\":\"4572\", \"RPTDTRVFAIL\":\"3\", \"OPTRPTDTRVFAIL\":\"19\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0110\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:56\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"47872366\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9658\", \"MT\":\"87989\", \"MTSD\":\"86742\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"0\", \"MO\":\"9998\", \"MORV\":\"9997\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"25\", \"OPTRPT\":\"88134\", \"DTRPT\":\"24\", \"OPTDTRPT\":\"86655\", \"RPTRV\":\"25\", \"OPTRPTRV\":\"87439\", \"RPTDTRV\":\"24\", \"OPTRPTDTRV\":\"86655\", \"RPTDTRVSUCC\":\"8\", \"OPTRPTDTRVSUCC\":\"84979\", \"RPTDTRVFAIL\":\"16\", \"OPTRPTDTRVFAIL\":\"1676\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0100\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:44:14\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"78458193\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9377\", \"MT\":\"7679\", \"MTSD\":\"7229\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"32\", \"MORV\":\"32\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"7683\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"7651\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"7664\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"7651\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"7201\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"450\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0098\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:41:01\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"92614977\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9526\", \"MT\":\"1626\", \"MTSD\":\"1584\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"25\", \"MORV\":\"25\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"1618\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"1608\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"1617\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"1608\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"1549\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"59\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0112\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:35:01\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"90174184\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9677\", \"MT\":\"5182\", \"MTSD\":\"5105\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"1\", \"MTAVGDLY\":\"1\", \"MO\":\"1268\", \"MORV\":\"1268\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"1\", \"OPTRPT\":\"5174\", \"DTRPT\":\"1\", \"OPTDTRPT\":\"5110\", \"RPTRV\":\"1\", \"OPTRPTRV\":\"5138\", \"RPTDTRV\":\"1\", \"OPTRPTDTRV\":\"5110\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"5015\", \"RPTDTRVFAIL\":\"1\", \"OPTRPTDTRVFAIL\":\"95\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0133\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-03-25 21:42:34\", \"OUTTM\":\"2018-03-25 21:42:34\", \"LASTVST\":\"2019-12-07 23:19:43\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"99360000\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8974\", \"MT\":\"234\", \"MTSD\":\"234\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"203\", \"MTMAXDLY\":\"203\", \"MTAVGDLY\":\"203\", \"MO\":\"0\", \"MORV\":\"0\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"226\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"216\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"223\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"216\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"210\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"6\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0210\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-10-30 01:57:17\", \"OUTTM\":\"2018-10-30 01:57:17\", \"LASTVST\":\"2019-12-07 23:48:42\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"18480075\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9463\", \"MT\":\"33836\", \"MTSD\":\"33418\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"2\", \"MTMAXDLY\":\"196\", \"MTAVGDLY\":\"105\", \"MO\":\"66\", \"MORV\":\"66\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"34128\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"33155\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"34128\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"33155\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"32021\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"1134\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0175\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\"218.17.39.34:51172\", \"STATUS\":\"7\", \"INTM\":\"2019-07-11 11:58:53\", \"OUTTM\":\"2019-07-11 12:09:17\", \"LASTVST\":\"2019-12-08 00:00:00\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"4997170\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9271\", \"MT\":\"118648\", \"MTSD\":\"120466\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"1\", \"MO\":\"99\", \"MORV\":\"99\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"825\", \"OPTRPT\":\"117067\", \"DTRPT\":\"802\", \"OPTDTRPT\":\"114556\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"0\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"0\", \"RPTDTRVSUCC\":\"56\", \"OPTRPTDTRVSUCC\":\"109953\", \"RPTDTRVFAIL\":\"746\", \"OPTRPTDTRVFAIL\":\"4603\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0115\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:55\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"49934104\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8111\", \"MT\":\"42620\", \"MTSD\":\"36869\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"2415\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"12\", \"MORV\":\"12\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"43202\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"41560\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"42344\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"41560\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"34106\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"7454\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/2413/0\" },{ \"USERID\":\"JG0136\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-07-01 06:39:09\", \"OUTTM\":\"2018-07-01 06:39:09\", \"LASTVST\":\"2019-12-07 23:12:40\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"2\", \"FEE\":\"50009693\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8646\", \"MT\":\"1433\", \"MTSD\":\"1276\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"54\", \"MTMAXDLY\":\"80\", \"MTAVGDLY\":\"0\", \"MO\":\"11\", \"MORV\":\"11\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"5805\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"1414\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"5800\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"1414\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"1239\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"175\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" }]</EVTCONT></EVENT>";
       //msgTemp = "<EVENT><EVTID>1500</EVTID><EVTTYPE>99</EVTTYPE><EVTTM>2019-12-08 00:00:00.259</EVTTM><VERSION>V4.5</VERSION><EVTCONT>[{ \"USERID\":\"JG0109\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:59:53\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"61265537\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"8677\", \"MT\":\"106788\", \"MTSD\":\"94114\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"0\", \"MTMAXDLY\":\"0\", \"MTAVGDLY\":\"0\", \"MO\":\"1075\", \"MORV\":\"1075\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"0\", \"OPTRPT\":\"107153\", \"DTRPT\":\"0\", \"OPTDTRPT\":\"106051\", \"RPTRV\":\"0\", \"OPTRPTRV\":\"107153\", \"RPTDTRV\":\"0\", \"OPTRPTDTRV\":\"106051\", \"RPTDTRVSUCC\":\"0\", \"OPTRPTDTRVSUCC\":\"92667\", \"RPTDTRVFAIL\":\"0\", \"OPTRPTDTRVFAIL\":\"13384\", \"RPTNORV\":\"0\", \"RPTMEMNORV\":\"0\", \"RPTFILENORV\":\"0\", \"RPTDBNORV\":\"0\", \"RPTSPD\":\"0\", \"MTLVLNOSD\":\"0/0/0/0/0/0/0/0/0/0\" },{ \"USERID\":\"JG0097\", \"JTYPE\":\"0\", \"LINKNUM\":\"0\", \"IP\":\":0\", \"STATUS\":\"7\", \"INTM\":\"2018-01-01 00:49:12\", \"OUTTM\":\"2018-01-01 00:49:12\", \"LASTVST\":\"2019-12-07 23:58:47\", \"OUTCNT\":\"0\", \"FEEFLAG\":\"1\", \"FEE\":\"87184257\", \"GLBFEE\":\"0\", \"SUCCRATE\":\"9799\", \"MT\":\"9673\", \"MTSD\":\"9702\", \"MTNOSD\":\"0\", \"MTMEMNOSD\":\"0\", \"MTFILENOSD\":\"0\", \"MTVFYNOSD\":\"0\", \"MTLVL0NOSD\":\"0\", \"MTSPD\":\"0\", \"MTMINDLY\":\"1\", \"MTMAXDLY\":\"2\", \"MTAVGDLY\":\"2\", \"MO\":\"1\", \"MORV\":\"1\", \"MONORV\":\"0\", \"MOMEMNORV\":\"0\", \"MOFILENORV\":\"0\", \"MODBNORV\":\"0\", \"MOSPD\":\"0\", \"RPT\":\"12\", \"OPTRPT\":\"9639\", ";
       String msg = msgTemp;
       
       Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
       
       long b = System.currentTimeMillis();
       
       int count = 400000;
       int index = 0;
       System.out.println(topic+"-"+key+"-开始："+count);
       while(index < count) {
    	   index++;
    	   ProducerRecord<String,String> record = new ProducerRecord<String,String>(topic, key, msg);
    	   //ProducerRecord<Integer,String> record = new ProducerRecord<Integer,String>(topic, msg);
//    	   KafkaSendCallback callback = new KafkaSendCallback();
    	   producer.send(record);
       }
       System.out.println(topic+"-"+key+"-结束："+index);
       long e = System.currentTimeMillis();
	   long t = e-b;
	   System.out.println(topic+"-"+key+"-耗时："+(t));
       
       
       producer.close();
   }
   
}