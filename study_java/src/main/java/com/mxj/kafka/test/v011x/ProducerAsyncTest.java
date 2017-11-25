package com.mxj.kafka.test.v011x;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Rob Jiang
 * @date 2017年10月25日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class ProducerAsyncTest {
	
	
	
	public static void main(String[] args) {
		
		
		InputStream resourceAsStream = ProducerAsyncTest.class.getClassLoader().getResourceAsStream("producer.properties");
		Properties properties= new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			
			int i=ThreadLocalRandom.current().nextInt(50000000);
			String key="k"+Integer.toString(i);
			String value="v"+Integer.toString(i);
			
			
			Producer<String, String> producer = new KafkaProducer<>(properties);
			ProducerRecord<String, String> record= new ProducerRecord<String, String>("new-topic", key, value);
			producer.send(record,new MyCallback(System.currentTimeMillis(), key, value));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		
		
	}

}

class MyCallback implements Callback{
	
	static final Logger logger = LoggerFactory.getLogger(MyCallback.class);
	private Object key;
	private Object value;
	private Long sendTime;
	
	public MyCallback(Long sendTime,Object key,Object value){
		this.value=value;
		this.key=key;
		this.sendTime=sendTime;
		
		
	}
	

	/* (non-Javadoc)
	 * @see org.apache.kafka.clients.producer.Callback#onCompletion(org.apache.kafka.clients.producer.RecordMetadata, java.lang.Exception)
	 */
	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub
		
		logger.error("now is {},message is {},take away {} ms. partition is {},offset is {}",
				System.currentTimeMillis(),this.value,System.currentTimeMillis()-this.sendTime,metadata.partition(),metadata.offset());
		
	}
	
}
