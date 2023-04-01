package com.github.amitsureshchandra.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
public class BeanConfig {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
