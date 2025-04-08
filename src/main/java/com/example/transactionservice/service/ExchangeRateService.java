package com.example.transactionservice.service;

import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;

public interface ExchangeRateService {

    ExchangeRate getExchangeRate(Currency from);

}
