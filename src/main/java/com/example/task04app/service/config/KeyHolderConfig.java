/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:19
 * @version V 1.0.0
 */

package com.example.task04app.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * The type Key holder config.
 */
@Configuration
public class KeyHolderConfig {

    /**
     * Key holder key holder.
     *
     * @return the key holder
     */
    @Bean
    public KeyHolder keyHolder(){

        return new GeneratedKeyHolder();
    }
}
