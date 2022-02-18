package com.example.task04app.external.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Configuration
@Validated
@Getter
@Setter
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "integration.open-library")
public class OpenLibraryConfig {

    @NotBlank
    String baseUrl;
}
