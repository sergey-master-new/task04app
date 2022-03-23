/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:02
 * @version V 1.0.0
 */

package com.example.task04app.external.alfabank.dto;

import com.example.task04app.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Book price in currencies dto contains price in BYN and currencies EUR, USD, RUB.
 */
@Data
@AllArgsConstructor
public class BookPriceInCurrenciesDto {

    private BookDto bookDto;

    private Map<String, BigDecimal> priceInCurrenciesMap;

    /**
     * Instantiates a new Book price in currencies dto.
     */
    public BookPriceInCurrenciesDto() {

        this.priceInCurrenciesMap = new HashMap<>();
    }
}
