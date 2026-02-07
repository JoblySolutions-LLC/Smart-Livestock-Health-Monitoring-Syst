package com.livestock.ingestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.Instant;

@SpringBootApplication
public class DeviceIngestorApplication {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" Smart Livestock Ingestor Starting ");
        System.out.println("====================================");

        SpringApplication.run(DeviceIngestorApplication.class, args);

        System.out.println("====================================");
        System.out.println(" Ingestor Service Running Successfully ");
        System.out.println(" Started at: " + Instant.now());
        System.out.println("====================================");
    }

    // Runs after application startup
    @PostConstruct
    public void init() {

        System.out.println("------------------------------------");
        System.out.println(" Device Ingestor Initialized ");
        System.out.println(" Ready to receive IoT data ");
        System.out.println("------------------------------------");
    }
}
