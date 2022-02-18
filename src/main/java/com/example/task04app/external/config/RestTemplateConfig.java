package com.example.task04app.external.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        return restTemplateBuilder().build();
    }

    @Bean
    @DependsOn(value = {"customRestTemplateCustomizer"})
    public RestTemplateBuilder restTemplateBuilder() {

        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }

    @Bean
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {

        return new CustomRestTemplateCustomizer();
    }
}
