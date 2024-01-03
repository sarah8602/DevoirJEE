package com.mcommande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// pour Spring Cloud Config
@EnableConfigurationProperties
// pour Eureka Client
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCommandesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCommandesApplication.class, args);
    }

}
