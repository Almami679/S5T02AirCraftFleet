package com.microservice.planes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicePlanesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePlanesApplication.class, args);
	}

}
