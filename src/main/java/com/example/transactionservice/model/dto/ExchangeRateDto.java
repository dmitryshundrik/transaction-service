package com.example.transactionservice.model.dto;

import java.math.BigDecimal;

public record ExchangeRateDto(
        String symbol,

        BigDecimal rate,

        String timestamp
) {
}
