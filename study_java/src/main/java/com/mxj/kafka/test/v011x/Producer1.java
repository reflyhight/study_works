package com.mxj.kafka.test.v011x;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Rob Jiang
 * @date 2017年10月15日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class Producer1 {
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
		 props.put("bootstrap.servers", "hado87:9092,hado88:9092");//brokers
		 props.put("acks", "all");//生产消息时，需要消息确认。all表示每条都需要确认，新版本加入all
		 props.put("retries", 0);//连接失败，重试次数
		 props.put("batch.size", 16384);//批量提交16kb
		 props.put("linger.ms", 1);//峰值延时1ms
		 props.put("buffer.memory", 33554432);//缓冲区内存最大为32M
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");//序列化类
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");//序列化类
		 props.put("max.request.size", 50);
//		 props.put("metric.reporters", "org.apache.kafka.common.metrics.JmxReporter");
		 
		 Logger logger = LoggerFactory.getLogger(Producer1.class);
		 
		 
		 Producer<String, String> producer = new KafkaProducer<String, String>(props);
			while(true){
			 int i=ThreadLocalRandom.current().nextInt(50000000);
			
			 logger.info("key=========:"+i);
			 
			 String key="k"+Integer.toString(i);
			 String value="v"+Integer.toString(i);
			 
			 
			 producer.send(new ProducerRecord<String, String>("my-topic", key,value));
		 
			 try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

//		 producer.close();
	}
	
	
}
