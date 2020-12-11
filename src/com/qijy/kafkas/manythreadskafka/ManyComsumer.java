package com.qijy.kafkas.manythreadskafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/*
 * @ Description   :  多消费者
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/9 20:33
 */
public class ManyComsumer {
    private KafkaConsumer<String, String> kafkaConsumer;
    private String groupid;
    private String topic;
    public ManyComsumer(String groupid,String topic) {
        this.groupid = groupid;
        this.topic = topic;
        init(groupid,topic);
    }

    private void init(String groupid,String topic) {
        Properties properties = getProperties();
        kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }
    /*
     * @ Description   :  消费数据
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/10 9:13
     */
    public ConsumerRecords<String,String> poll(){
        ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofMillis(100));
        return poll;
    }

    /*
     * @ Description   :  获取分区数
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/10 9:13
     */
    public int getPartitionNum(){
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);
        return partitionInfos.size();
    }

    public void commit(){
        kafkaConsumer.commitSync();
    }
    /*
     * @ Description   :  配置
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/10 11:29
     */
    public Properties getProperties(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("enable.auto.commit", false);
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("max.poll.records", 100);
        properties.put("auto.offset.reset", "earliest");
        properties.put("group.id",groupid);
        return properties;
    }


}
