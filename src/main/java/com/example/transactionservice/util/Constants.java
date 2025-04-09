package com.example.transactionservice.util;

import com.example.transactionservice.model.enums.Currency;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String GETTING_EXCHANGE_RATE_FAIL_MESSAGE = "Get current getCurrentExchangeRate rate by api " +
            "failed: %s";

    public static final Currency BASE_EXCHANGE_CURRENCY = Currency.USD;
}
