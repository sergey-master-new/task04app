/*
 * @author S.Maevsky
 * @since 04.03.2022, 15:58
 * @version V 1.0.0
 */

package com.example.task04app.external.alfabank.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;


/**
 * The type Rest template alfa bank config.
 */
@AllArgsConstructor
@Configuration
public class RestTemplateAlfaBankConfig {

    private final AlfaBankConfig config;

    /**
     * Rest template for AlfaBank.
     *
     * @return the rest template.
     */
    @Bean("restTemplateAlfaBank")
    public RestTemplate restTemplateAlfaBank() {

        return new RestTemplateBuilder()
                .additionalInterceptors(new AlfaBankRequestInterceptor())
                .uriTemplateHandler(new DefaultUriBuilderFactory(config.getBaseUrl()))
                .setConnectTimeout(Duration.ofMillis(config.getConnectTimeout()))
                .setReadTimeout(Duration.ofMillis(config.getReadTimeout()))
                .build();
    }

}
