package com.ecommerce.productservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration // This annotation not only create the object of this class and but also tells to execute all the method
// inside this class.
public class ApplicationConfiguration {
    @Bean // Indicating to Spring that create a bean and store it into Application Context or Container
    @LoadBalanced // This annotation is used to tell the Spring Cloud that this RestTemplate is used to call the services in a load balanced way.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Step 1 :- Create a bean for Redis Template and configure it.
    // Step 2 :- Create a bean for Redis Connection Factory and configure it.
    // Step 3 :- Inject the Redis Connection Factory into Redis Template.
    // Step 4 :- Inject the Redis Template into FakeStoreService.
    // Step 5 :- Use the Redis Template to store the data into Redis Cache.
    // Step 6 :- Use the Redis Template to fetch the data from Redis Cache.
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
