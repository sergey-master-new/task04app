/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:19
 * @version V 1.0.0
 */

package com.example.task04app.service.external;

import com.example.task04app.external.alfabank.pojo.NationalRate;
import com.example.task04app.external.alfabank.pojo.Rate;

import java.util.List;

/**
 * The interface Alfa bank exchange client.
 */
public interface AlfaBankExchangeClient {

    /**
     * Gets rates for EUR, USD, RUB.
     *
     * @return the rates
     */
    List<Rate> getRates();

    /**
     * Gets national rates.
     *
     * @return the national rates
     */
    List<NationalRate> getNationalRates();

}
