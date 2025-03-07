package com.ecommerce.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation is used to enable the auto-configuration feature of the Spring Boot application.
public class ProductServiceApplication {

    // This is the main method that is used to run the Spring Boot application.
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
