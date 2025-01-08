package com.ecommerce.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // This annotation not only create the object of this class and but also tells to execute all the method
// inside this class.
public class ApplicationConfiguration {
    @Bean // Indicating to Spring that create a bean and store it into Application Context or Container
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
