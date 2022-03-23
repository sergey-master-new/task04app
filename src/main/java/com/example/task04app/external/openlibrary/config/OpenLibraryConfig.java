/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:06
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


/**
 * The type Open library config.
 */
@Configuration
@Validated
@Getter
@Setter
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "integration.openlibrary")
public class OpenLibraryConfig {

    /**
     * The Base url.
     */
    @NotBlank
    String baseUrl;
}
