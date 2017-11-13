package com.mxj.kafka.test.v011x;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * 
 * 
 * @author Rob Jiang
 * @date 2017年10月15日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class Consumer1 {
	
	public static void main(String[] args) {
		   Properties props = new Properties();
		     props.put("bootstrap.servers", "hado87:9092");//brokers
		     props.put("group.id", "test");//消费者组ID
		     
		     //enable.auto.commit为true时，offset将会自动提交,由程序自动管理消费者offset
		     props.put("enable.auto.commit", "true");
		     //auto.commit.interval.ms表示offset自动提交的时间间隔，单位ms,前提是enable.auto.commit 为true
		     props.put("auto.commit.interval.ms", "1000");
		     
//		     props.put("enable.auto.commit", false);
		     
		     //序列化
		     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		     
		     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		     String topic="my-topic";
		     
//		     TopicPartition p = new TopicPartition(topic,0);
		     
		     consumer.subscribe(Arrays.asList(topic));//可以订阅多个topic
//		     consumer.assign(Arrays.asList(p));
//		     consumer.seek(new TopicPartition(topic, 0), 415329L);
		     
		     while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         for (ConsumerRecord<String, String> record : records)
		             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		     }
	}

}
