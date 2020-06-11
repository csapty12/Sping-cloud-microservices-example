package com.microservicstuts.example1PhotoAppDiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Example1PhotoAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Example1PhotoAppDiscoveryServiceApplication.class, args);
	}

}
