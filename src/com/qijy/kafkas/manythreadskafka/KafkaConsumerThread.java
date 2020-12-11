package com.qijy.kafkas.manythreadskafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/*
 * @ Description   :  
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/10 15:02
 */
public class KafkaConsumerThread implements Runnable {
    private KafkaConsumer<String,String> kafkaConsumer;

    public KafkaConsumerThread(String topic, Properties prop) {
        kafkaConsumer = new KafkaConsumer(prop);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("线程:"+Thread.currentThread().getName()+";topic:"+record.topic()+";key:"+record.key()+";value:"+record.value());
                kafkaConsumer.commitSync();
            }
        }
    }
}
