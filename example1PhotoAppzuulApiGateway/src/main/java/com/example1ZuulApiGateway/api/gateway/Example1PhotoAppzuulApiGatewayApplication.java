package com.example1ZuulApiGateway.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy  //this enables the application to be a Zuul API gateway
public class Example1PhotoAppzuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Example1PhotoAppzuulApiGatewayApplication.class, args);
	}

}
