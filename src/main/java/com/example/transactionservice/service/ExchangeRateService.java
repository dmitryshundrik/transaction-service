package com.example.transactionservice.service;

import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;
import java.time.ZonedDateTime;

/**
 * Service interface for handling exchange rates.
 * Provides methods for retrieving the current exchange rate.
 */
public interface ExchangeRateService {

    /**
     * Retrieves the current exchange rate for a specified currency on a given transaction date.
     *
     * @param from the currency from which the conversion is made
     * @param transactionDate the date of the transaction for which the rate is required
     * @return an {@link ExchangeRate} object containing exchange rate information
     */
    ExchangeRate getActualExchangeRate(Currency from, ZonedDateTime transactionDate);

}
