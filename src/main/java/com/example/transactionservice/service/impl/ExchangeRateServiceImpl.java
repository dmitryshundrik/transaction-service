package com.example.transactionservice.service.impl;

import com.example.transactionservice.exception.ExchangeRateServiceException;
import com.example.transactionservice.model.dto.ExchangeRateDto;
import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.repository.ExchangeRateRepository;
import com.example.transactionservice.service.ExchangeRateService;
import com.example.transactionservice.service.client.ExchangeRateClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;

import static com.example.transactionservice.util.Constants.BASE_EXCHANGE_CURRENCY;
import static com.example.transactionservice.util.Constants.GETTING_EXCHANGE_RATE_FAIL_MESSAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateClient exchangeRateClient;

    @Value("${exchange-rate.api-key}")
    private String apiKey;

    @Override
    @Transactional(readOnly = true)
    public ExchangeRate getExchangeRate(Currency from) {
        log.debug("Fetching exchange rate for currency: {}", from);
        return exchangeRateRepository.findByCurrencyFromAndCurrencyTo(from, Currency.USD)
                .orElseThrow(() -> {
                    log.error("Exchange rate not found for currency: {}", from);
                    return new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE);
                });
    }

    @Scheduled(cron = "${exchange-rate.schedule.cron.update}")
    protected void updateExchangeRates() {
        log.info("Starting exchange rate update for all currencies");
        for (Currency currency : Currency.values()) {
            if (!BASE_EXCHANGE_CURRENCY.equals(currency)) {
                ExchangeRateDto dto = getExchangeRateFromClient(currency.name() + "/" + BASE_EXCHANGE_CURRENCY);
                ExchangeRate entity = exchangeRateRepository.findByCurrencyFromAndCurrencyTo(currency, BASE_EXCHANGE_CURRENCY)
                        .orElseThrow(() -> {
                            log.error("No existing exchange rate found for {}/{}", currency, BASE_EXCHANGE_CURRENCY);
                            return new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE);
                        });
                entity.setRate(dto.rate());
                entity.setUpdatedAt(ZonedDateTime.now());
                exchangeRateRepository.save(entity);
                log.debug("Updated exchange rate for {}/{}: {}", currency, BASE_EXCHANGE_CURRENCY, dto.rate());
            }
        }
        log.info("Finished exchange rate update");
    }

    private ExchangeRateDto getExchangeRateFromClient(String symbol) {
        log.debug("Requesting exchange rate from client for symbol: {}", symbol);
        try {
            ExchangeRateDto dto = exchangeRateClient.getExchangeRate(symbol, apiKey);
            log.debug("Received exchange rate for {}: {}", symbol, dto.rate());
            return dto;
        } catch (Exception e) {
            log.error("Failed to fetch exchange rate for symbol {}: {}", symbol, e.getMessage(), e);
            throw new ExchangeRateServiceException(GETTING_EXCHANGE_RATE_FAIL_MESSAGE.formatted(e.getMessage()));
        }
    }
}
