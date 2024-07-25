package com.grocery.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GroceryApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryApiGatewayApplication.class, args);
    }
}
