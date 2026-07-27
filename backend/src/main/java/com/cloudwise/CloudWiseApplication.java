package com.cloudwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the CloudWise Spring Boot application.
 *
 * <p>This class bootstraps the entire application, including the embedded
 * Tomcat server, Spring Data JPA, and the H2 in-memory database.</p>
 */
@SpringBootApplication
public class CloudWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudWiseApplication.class, args);
    }
}
