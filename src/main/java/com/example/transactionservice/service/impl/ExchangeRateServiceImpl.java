package com.example.transactionservice.service.impl;

import com.example.transactionservice.exception.ExchangeRateServiceException;
import com.example.transactionservice.model.dto.ExchangeRateDto;
import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.repository.ExchangeRateRepository;
import com.example.transactionservice.service.ExchangeRateService;
import com.example.transactionservice.service.client.ExchangeRateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;

import static com.example.transactionservice.util.Constants.BASE_EXCHANGE_CURRENCY;
import static com.example.transactionservice.util.Constants.GETTING_EXCHANGE_RATE_FAIL_MESSAGE;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateClient exchangeRateClient;

    @Value("${exchange-rate.api-key}")
    private String apiKey;

    @Override
    @Transactional(readOnly = true)
    public ExchangeRate getExchangeRate(Currency from) {
        return exchangeRateRepository.findByCurrencyFromAndCurrencyTo(from, Currency.USD)
                .orElseThrow(() -> new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE));
    }

    @Scheduled(cron = "${exchange-rate.schedule.cron.update}")
    private void updateExchangeRates() {
        for (Currency currency : Currency.values()) {
            if (!BASE_EXCHANGE_CURRENCY.equals(currency)) {
                ExchangeRateDto dto = getExchangeRateFromClient(currency.name() + "/" + BASE_EXCHANGE_CURRENCY);
                ExchangeRate entity = exchangeRateRepository.findByCurrencyFromAndCurrencyTo(currency, BASE_EXCHANGE_CURRENCY)
                        .orElseThrow(() -> new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE));
                entity.setRate(dto.rate());
                entity.setUpdatedAt(ZonedDateTime.now());
                exchangeRateRepository.save(entity);
            }
        }
    }

    private ExchangeRateDto getExchangeRateFromClient(String symbol) {
        try {
            return exchangeRateClient.getExchangeRate(symbol, apiKey);
        } catch (Exception e) {
            throw new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE.formatted(e.getMessage()));
        }
    }
}
