package com.vzwcorp.pricinglab.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallback  implements Callback{

	@Override
	public void onCompletion(RecordMetadata arg0, Exception arg1) {
		if (arg0 != null)
			System.out.println(arg0);
		else
			System.out.println("error: "+ arg1);
	}

}
