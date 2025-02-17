package com.microservice.battle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceBattleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBattleApplication.class, args);
	}

}
