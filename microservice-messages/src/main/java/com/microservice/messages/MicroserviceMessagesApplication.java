package com.microservice.messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceMessagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMessagesApplication.class, args);
	}

}
