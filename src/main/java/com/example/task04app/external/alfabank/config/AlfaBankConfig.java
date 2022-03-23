/*
 * @author S.Maevsky
 * @since 04.03.2022, 15:57
 * @version V 1.0.0
 */

package com.example.task04app.external.alfabank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


/**
 * The type Alfa bank config.
 */
@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "integration.alfabank")
@Configuration
public class AlfaBankConfig {

    /**
     * The Base url.
     */
    @NotBlank
    String baseUrl;

    /**
     * The Connect timeout.
     */
    @Positive
    Integer connectTimeout;

    /**
     * The Read timeout.
     */
    @Positive
    Integer readTimeout;
}

