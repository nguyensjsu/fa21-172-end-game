package com.example.payments.springpayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class SpringPaymentsApplication {
	
	//Source:  https://dev.to/habeebcycle/spring-value-annotation-tricks-1a80
	@Value("${spring.rabbitmq.host}")
	String host;		
	@Value("${spring.rabbitmq.username}")
	String username;
	@Value("${spring.rabbitmq.password}")
	String password;
			
	public static void main(String[] args) {

		SpringApplication.run(SpringPaymentsApplication.class, args);
	}
}
