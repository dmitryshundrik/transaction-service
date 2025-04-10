package com.example.transactionservice.util;

import com.example.transactionservice.model.enums.Currency;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String GETTING_EXCHANGE_RATE_FAIL_MESSAGE = "Get current getActualExchangeRate rate by api " +
            "failed: %s";

    public static final Currency EXCHANGE_CURRENCY_USD = Currency.USD;

    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_YMDHMSX = "yyyy-MM-dd HH:mm:ssX";
}
