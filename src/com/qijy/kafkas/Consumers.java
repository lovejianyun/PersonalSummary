package com.qijy.kafkas;

import kafka.tools.ConsumerOffsetChecker;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class Consumers {

    //消费者
    private KafkaConsumer<String, String> consumer;
    //消费的分区
    List<TopicPartition> partitions;
    //消费组
    private String groupid = "";

    private String topic;





    public Consumers(String groupid, Collection<String> topics){
        this.groupid = groupid;
        consumer = new KafkaConsumer<String, String>(getProperties());
        consumer.subscribe(topics);
    }


    public Consumers(String groupid,String topic){
        this.groupid = groupid;
        this.topic = topic;
        consumer = new KafkaConsumer<String, String>(getProperties());
        consumer.subscribe(Arrays.asList(topic));
    }
    /**
     * 实例化kafka客户端
     * @param groupid
     * @param partitions
     */
//	public Consumer(String groupid,List<TopicPartition> partitions){
//		this.groupid = groupid;
//		this.partitions = partitions;
//		consumer = new KafkaConsumer<String, String>(getProperties());
//		consumer.assign(partitions);
//	}

    /**
     * 获取数据
     * @param timeout
     * @return
     */
    public ConsumerRecords<String,String> poll(long timeout){
        //如果没有创建消费者连接，则返回空
        if(null == consumer){
            return new ConsumerRecords<>(new HashMap<TopicPartition, List<ConsumerRecord<String, String>>>());
        }
        ConsumerRecords<String, String> records = consumer.poll(timeout);
        if(records.count() <= 0){
            return records;
        }
        //系统默认分配分区和offset的消费者，使用API默认的手动提交方式
        return records;
    }

    /**
     * 提交offset
     */
    public void commit(){
        if(consumer != null){
            try{
                consumer.commitSync();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭客户端
     */
    public void close(){
        if(consumer != null){
            try{
                this.consumer.close();
                this.consumer = null;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 消费者kafka配置
     * @return
     */
    public Properties getProperties(){
        Properties props = new Properties();
        //kafka地址配置
       // props.put("bootstrap.servers", "192.169.6.137:9092,192.169.6.25:9092,192.169.6.27:9092");
        props.put("bootstrap.servers", "192.169.0.238:9092,192.169.0.239:9092,192.169.0.240:9092");
        /**
         *  earliest
         当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
         latest
         当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
         none
         topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
         */
        //当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //消费组和消费offset提交配置
        props.put("group.id", this.groupid);//消费组Id
        props.put("enable.auto.commit", "false"); //true自动提交
        //props.put("auto.commit.interval.ms", "1000"); //offset提交周期

        //key-values格式配置
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //拉取数据的最大限制
        props.put("max.poll.records",20);

        //消费数据会话配置
        props.put("request.timeout.ms", 20000);
        props.put("session.timeout.ms", 10000);
        props.put("heartbeat.interval.ms", 5000);
        return props;
    }


    public Consumers(){
        consumer = new KafkaConsumer<String, String>(getProperties());
    }

    public KafkaConsumer<String, String> getConsumer(){
        return this.consumer;
    }


    /**
     * 获取消费主题的分区
     * @param
     * @param
     * @return
     */
    public List<TopicPartition> getTopicPartitions(){
        Consumers consumer = new Consumers();
        List<TopicPartition> tpList = new ArrayList<>();
        try{
            List<PartitionInfo> pinfos = consumer.consumer.partitionsFor(topic);
            for(PartitionInfo pinfo:pinfos){
                TopicPartition tp = new TopicPartition(pinfo.topic(), pinfo.partition());
                tpList.add(tp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            consumer.close();
        }
        return tpList;
    }

    public static void getResult(String topic){
        Consumers consumer = new Consumers();
        List<TopicPartition> tpList = new ArrayList<>();

        List<PartitionInfo> pinfos = consumer.consumer.partitionsFor(topic);

        for (PartitionInfo pinfo : pinfos) {
            TopicPartition tp = new TopicPartition(pinfo.topic(), pinfo.partition());
            tpList.add(tp);
        }
        Map<TopicPartition, Long> endOffsets  = consumer.consumer.endOffsets(tpList);
        Map<TopicPartition, Long> offsets = consumer.consumer.beginningOffsets(tpList);
        for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
            TopicPartition key = entry.getKey();
            OffsetAndMetadata committed = consumer.consumer.committed(key);
            long offset = committed.offset();
            int partition = key.partition();
//            Long aLong = offsets.get(key);
            long xx = entry.getValue() - offset;
            System.out.println("partition:" + partition + "lag:" + xx);
        }

    }

    public static Long getLag(String groupid,String topic){
        Consumers consumer = new Consumers(groupid,topic);
//        HashMap<String, Long> result = new HashMap<>();
        Long lag = 0L;
        List<TopicPartition> tpList = new ArrayList<>();
        List<PartitionInfo> pinfos = consumer.consumer.partitionsFor(topic);
        for(PartitionInfo pinfo:pinfos){
            TopicPartition tp = new TopicPartition(pinfo.topic(), pinfo.partition());
            tpList.add(tp);
        }
        Map<TopicPartition, Long> endOffsets = consumer.consumer.endOffsets(tpList);
        Set<Map.Entry<TopicPartition,Long>> entrySet = endOffsets.entrySet();
        for (Map.Entry<TopicPartition, Long> entry : entrySet) {
            TopicPartition topicPartition = entry.getKey();
            Long end = entry.getValue();
            OffsetAndMetadata committed = consumer.consumer.committed(topicPartition);
            long offset = committed.offset();
            lag += end-offset;
        }
        return lag;
    }
}
