package com.qijy.kafkas.manythreadskafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.network.Send;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/*
 * @ Description   :  kafka多个生产者
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/9 20:33
 */
public class ManyProduct {
    private KafkaProducer kafkaProducer;

    public ManyProduct() {
        init();
    }

    private void init(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer",StringSerializer.class.getName());
        properties.put("acks", "all");
        properties.put("batch.size", 563840);
        properties.put("buffer.memory", 93554432);
        properties.put("retries",0);
        kafkaProducer = new KafkaProducer(properties);
    }

    public void send(String topic,String msg){
        kafkaProducer.send(new ProducerRecord(topic, msg));
    }
}
