/*
 * @author S.Maevsky
 * @since 04.03.2022, 15:57
 * @version V 1.0.0
 */

package com.example.task04app.external.alfabank.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;


import java.io.IOException;


/**
 * The type Alfa bank request interceptor.
 */
@Slf4j
public class AlfaBankRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    @NonNull
    public ClientHttpResponse intercept(
            @NonNull HttpRequest request, @NonNull byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request);

        ClientHttpResponse response = execution.execute(request, body);
        log.trace("Response status Alfa Bank: {}", response.getStatusCode().value());

        return response;
    }

    private void logRequestDetails(HttpRequest request) {

        log.trace("Headers: {}", request.getHeaders());
        log.trace("Request Method: {}", request.getMethod());
        log.trace("Request URI: {}", request.getURI());
    }
}
