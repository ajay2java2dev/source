package com.spring.jaxb.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JaxbConfig {

	private Map<String,Object> properties;
	
	public JaxbConfig() {
		properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}
	
	@Bean
	public Jaxb2Marshaller createJaxb2Marshaller (@Value("${context.path}")final String contextPath,
			@Value("${schema.location}")final Resource schemaResource) {

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath(contextPath);
		jaxb2Marshaller.setSchema(schemaResource);
		jaxb2Marshaller.setMarshallerProperties(properties);
		
		return jaxb2Marshaller;
	}
	
	
	
	
}