package com.microservice.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceRankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRankingApplication.class, args);
	}

}
