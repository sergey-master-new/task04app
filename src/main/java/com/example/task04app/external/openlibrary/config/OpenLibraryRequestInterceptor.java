/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:06
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


/**
 * The type Open library request interceptor.
 */
@Slf4j
public class OpenLibraryRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        logRequestDetails(request);

        ClientHttpResponse response = execution.execute(request, body);
        log.trace("Response status open library: {}", response.getStatusCode().value());

        return response;
    }

    private void logRequestDetails(HttpRequest request) {

        log.trace("Headers: {}", request.getHeaders());
        log.trace("Request Method: {}", request.getMethod());
        log.trace("Request URI: {}", request.getURI());
    }
}
