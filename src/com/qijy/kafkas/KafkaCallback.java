package com.qijy.kafkas;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @ Description   :  kafka回调函数
 * @ Author        :  qijy
 * @ CreateDate    :  2020/8/27 16:17
 */
public class KafkaCallback  implements Callback {
    private Logger logger = LoggerFactory.getLogger(KafkaCallback.class);
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println(("topic:{" + recordMetadata.topic() + "}   ;offset:{" + recordMetadata.offset() + "}    ;partition:{" + recordMetadata.partition() + "}"));
    }
}
