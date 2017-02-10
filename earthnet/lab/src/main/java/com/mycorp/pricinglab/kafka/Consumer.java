package com.vzwcorp.pricinglab.kafka;


import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class Consumer implements Runnable {

	public void run() {
		String s;

		/* ConsumerIterator it = m_stream.iterator();
	        while (it.hasNext())
	            System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
	        System.out.println("Shutting down Thread: " + m_threadNumber);*/
	}

	public static void main(String[] args){

		Properties props = new Properties();
		//props.put("bootstrap.servers", "localhost:9092");
		props.put("bootstrap.servers", "113.128.161.235:9092");
		//props.put("group.id", "abc");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		String topic="device";
		//TopicPartition partition0 = new TopicPartition(topic, 0);
	    //TopicPartition partition1 = new TopicPartition(topic, 1);
	   //consumer.assign(Arrays.asList(partition0, partition1));		
		consumer.subscribe(Arrays.asList(topic));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
		}
	}
}

