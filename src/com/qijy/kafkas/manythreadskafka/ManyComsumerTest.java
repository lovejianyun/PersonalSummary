package com.qijy.kafkas.manythreadskafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * @ Description   :  多消费这测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/12/9 20:34
 */
public class ManyComsumerTest {
    public static void main(String[] args) {
//        oneThread();
        manyThread();
    }
    /*
     * @ Description   :  单线程版本
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/10 14:06
     */
    private static void oneThread() {
        ManyComsumer manyComsumer = new ManyComsumer("qijy","songli");
        while(true){
            ConsumerRecords<String, String> poll = manyComsumer.poll();
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println("topic:"+record.topic()+";key:"+record.key()+";value:"+record.value());
                manyComsumer.commit();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /*
     * @ Description   :  多线程版本
     * @ Author        :  qijy
     * @ CreateDate    :  2020/12/10 14:06
     */
    private static void manyThread(){
        ManyComsumer manyComsumer = new ManyComsumer("qijy01","songli");
        int partitionNum = manyComsumer.getPartitionNum();
        Properties properties = manyComsumer.getProperties();
        ExecutorService executorService = Executors.newFixedThreadPool(partitionNum);
        for (int i = 0; i <partitionNum ; i++) {
            KafkaConsumerThread kafkaConsumerThread = new KafkaConsumerThread("songli",properties);
            executorService.execute(kafkaConsumerThread);
        }

    }

}
