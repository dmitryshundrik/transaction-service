package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record LimitResponseDto(

        ZonedDateTime createdAt,

        BigDecimal amount,

        Currency currency
) {
}
