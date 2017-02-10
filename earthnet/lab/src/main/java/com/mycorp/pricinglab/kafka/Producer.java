package com.vzwcorp.pricinglab.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {

	public static void main(String[] args){
		Properties props = new Properties();
		String kafkaBrokerUrl="localhost"; //"113.128.161.235";
		//String kafkaBrokerUrl="113.128.161.235";
		if (args.length> 0)
			kafkaBrokerUrl=args[0];
//        props.put("metadata.broker.list", kafkaBrokerUrl+":2181");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBrokerUrl+":9092");
		props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.vzwcorp.pricinglab.kafka.ProducerPartitioner");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

		KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);
		
		boolean sync = false;
		String topic="test";
		String key = "mykey";
		String value = "myvalue";
		long l0=System.currentTimeMillis();
		System.out.println("Starting");
		int numLoops= 100;
		 for(int i = 0; i < numLoops; i++){
			 ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic, key+i, value+i);
			 producer.send(producerRecord, new ProducerCallback());
		 }
		 long l1=System.currentTimeMillis();
		 System.out.println("Done  Average is: "+ (l1-l0)+" msec");

		producer.close();
	}
}
