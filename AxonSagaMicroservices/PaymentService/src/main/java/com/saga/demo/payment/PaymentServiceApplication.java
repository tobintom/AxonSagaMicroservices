package com.saga.demo.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.thoughtworks.xstream.XStream;

@SpringBootApplication
public class PaymentServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public XStream xStream() {
	    XStream xStream = new XStream();
	    xStream.allowTypesByWildcard(new String[] { "com.saga.demo.**" });
	    return xStream;
	}
}
