/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:07
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.config;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The type Open library rest template customizer.
 */
@Component
public class OpenLibraryRestTemplateCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(new OpenLibraryRequestInterceptor());
    }
}
