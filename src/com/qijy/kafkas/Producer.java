package com.qijy.kafkas;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
 * @ Description   :  kafka生产者
 * @ Author        :  qijy
 * @ CreateDate    :  2020/8/27 16:55
 */
public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    private static KafkaProducer procuder = null;
    static {
        Properties props = new Properties();
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG,"192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "all");
        props.put("retries", 3);
        props.put("batch.size", 563840);
        props.put("linger.ms", 1000);
        props.put("compression.type", "lz4");
        props.put("buffer.memory", 93554432);
        props.put("max.request.size",10485760);
        props.put("retry.backoff.ms",500);
        procuder = new KafkaProducer<String, String>(props);
    }
    public static void execMsgSend(String topic, String key, String value, Callback callback) {
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,key,value);
        procuder.send(record,callback);
    }
    public static void execMsgSend(String topic, String key, String value) {
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,key,value);
        try {
            procuder.send(record);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void close(){
        procuder.close();
    }

}
