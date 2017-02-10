package com.spring.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.spring.jaxb.vo.Order;
import com.spring.jaxb.vo.Order.Address;

@SpringBootApplication
public class SpringJaxbApplication implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(SpringJaxbApplication.class);
	
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller; //check JaxbXConfig.java
	
	public static void main(String[] args) {
		logger.debug("Starting SpringJaxBApplication...");
		SpringApplication.run(SpringJaxbApplication.class, args);
		logger.debug("Starting SpringJaxBApplication...");
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.debug("Starting SpringJaxBApplication...");
		
		Address address = new Address();
		address.setCountry("");
		address.setCity("NJ");
		address.setLine1("");
		address.setLine2("");
		address.setLine3("");
		address.setName("");
		address.setPostcode("");
		address.setState("");

		Order order = new Order();
		order.setAddress(address);
		order.setOrderid("test123");
		order.setProduct("new product");
		
		StringWriter writer = new StringWriter();
		jaxb2Marshaller.marshal(order, new StreamResult(writer));
		String xml = writer.toString();
		System.out.println(xml);
		
		Order order1 = (Order)jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(xml)));
		if (order1!=null) {
			System.out.println(order1.getOrderid());
		}
	}
}