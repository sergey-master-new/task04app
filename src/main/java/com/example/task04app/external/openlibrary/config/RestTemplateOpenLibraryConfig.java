/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:07
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * The type Rest template open library config.
 */
@Configuration
public class RestTemplateOpenLibraryConfig {

    /**
     * Rest template for openlibrary.org.
     *
     * @return the rest template
     */
    @Bean("restTemplateOpenLibrary")
    public RestTemplate restTemplateOpenLibrary() {

        return restTemplateBuilder().build();
    }

    /**
     * Rest template builder rest template builder.
     *
     * @return the rest template builder
     */
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {

        return new RestTemplateBuilder(new OpenLibraryRestTemplateCustomizer());
    }
}
