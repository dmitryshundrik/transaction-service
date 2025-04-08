package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record LimitResponseDto(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        ZonedDateTime createdAt,

        BigDecimal amount,

        Currency currency
) {
}
