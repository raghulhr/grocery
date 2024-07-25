package com.grocery.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GroceryServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryServiceRegistryApplication.class, args);
    }

}
