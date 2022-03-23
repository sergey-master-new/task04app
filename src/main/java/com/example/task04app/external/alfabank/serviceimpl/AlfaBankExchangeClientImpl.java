/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:04
 * @version V 1.0.0
 */

package com.example.task04app.external.alfabank.serviceimpl;

import com.example.task04app.exception.ExternalRequestException;
import com.example.task04app.exception.ExternalResponseException;
import com.example.task04app.external.alfabank.pojo.NationalRate;
import com.example.task04app.external.alfabank.pojo.NationalRateListResponse;
import com.example.task04app.external.alfabank.pojo.Rate;
import com.example.task04app.external.alfabank.pojo.RateListResponse;
import com.example.task04app.service.external.AlfaBankExchangeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Alfa bank exchange client.
 */
@Slf4j
@Service
public class AlfaBankExchangeClientImpl implements AlfaBankExchangeClient {

    private final @Qualifier("restTemplateAlfaBank") RestTemplate restTemplate;

    private static final String END_POINT_GET_NATIONAL_RATES =
            "partner/1.0.1/public/nationalRates";

    private static final String END_POINT_GET_RATES =
            "partner/1.0.1/public/rates";

    /**
     * Instantiates a new Alfa bank exchange client.
     *
     * @param restTemplate the rest template for AlfaBank
     */
    public AlfaBankExchangeClientImpl(@Qualifier("restTemplateAlfaBank") RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public List<Rate> getRates() {

        return tryGetRatesFromAlfaBank();
    }

    @Override
    public List<NationalRate> getNationalRates() {

        return tryGetNationalRateFromAlfaBank();
    }

    private List<Rate> tryGetRatesFromAlfaBank() {

        RateListResponse response;

        try {

            response = restTemplate.getForObject(END_POINT_GET_RATES,
                    RateListResponse.class);

        } catch (RestClientException ex) {

            log.error("Exception when handling get rates from AlfaBank.");
            throw new ExternalRequestException("Exception when handling get rates from bank. " +
                    "Please try again later.");
        }

        checkOnNullResponse(response);

        return response.getRates();
    }


    private List<NationalRate> tryGetNationalRateFromAlfaBank() {

        NationalRateListResponse response;

        try {

            response = restTemplate.getForObject(END_POINT_GET_NATIONAL_RATES,
                    NationalRateListResponse.class);


        } catch (RestClientException ex) {

            log.error("Exception when handling get national rates from AlfaBank.");
            throw new ExternalRequestException("Exception when handling get rates from bank. " +
                    "Please try again later.");
        }

        checkOnNullResponse(response);

        return response.getRates();
    }

    private void checkOnNullResponse(NationalRateListResponse response) {

        if (response == null || response.getRates() == null) {

            log.error("Exception when getting up-to-date exchange rates. Response = null");
            throw new ExternalResponseException("Exception when getting exchange rates.");
        }
    }

    private void checkOnNullResponse(RateListResponse response) {

        if (response == null || response.getRates() == null) {

            log.error("Exception when getting up-to-date exchange rates. Response = null");
            throw new ExternalResponseException("Exception when getting exchange rates.");
        }
    }
}
