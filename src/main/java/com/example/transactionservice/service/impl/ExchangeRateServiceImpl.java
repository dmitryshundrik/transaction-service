package com.example.transactionservice.service.impl;

import com.example.transactionservice.repository.ExchangeRateRepository;
import com.example.transactionservice.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

}
