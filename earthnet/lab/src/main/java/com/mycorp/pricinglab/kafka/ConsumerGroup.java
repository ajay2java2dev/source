package com.vzwcorp.pricinglab.kafka;

import java.util.Properties;



import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ConsumerGroup {
	
	/*public static void main (String[] args ){
		Properties props = new Properties();
		 props.put("bootstrap.servers", "113.128.161.235:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		 KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
		  System.out.println("gotProducer "+ producer.metrics());
		 for(int i = 0; i < 100; i++){
			 ProducerRecord<String, String> producerRecord= new ProducerRecord<String, String>("device", Integer.toString(i), Integer.toString(i));
		     producer.send(producerRecord);
		     producer.
		     System.out.println("sadfdsaf");
		 }
		 System.out.println("closing");
		 producer.close();		
	}*/
	
	public static void main(String[] args){
		Properties props = new Properties();
		String kafkaBrokerUrl="113.128.161.235";
		if (args.length> 0)
			kafkaBrokerUrl=args[0];
        props.put("metadata.broker.list", kafkaBrokerUrl+":9092");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaBrokerUrl+":9092");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

		KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);
		
		boolean sync = false;
		String topic="device";
		String key = "mykey";
		String value = "myvalue";
		long l0=System.currentTimeMillis();
		System.out.println("Starting");
		int numLoops= 100000;
		 for(int i = 0; i < numLoops; i++){
			 ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic, key+i, value+i);
			 producer.send(producerRecord);
		 }
		 long l1=System.currentTimeMillis();
		 System.out.println("Done  Average is: "+ (l1-l0)+" msec");

		producer.close();
	}
}
