package com.example.transactionservice.client;

import com.example.transactionservice.model.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchange-rate", url = "${exchange-rate.url}")
public interface ExchangeRateClient {

    @GetMapping
    ExchangeRateDto getExchangeRate(@RequestParam("symbol") String symbol, @RequestParam("apikey") String apiKey);

}

