package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.example.transactionservice.util.Constants.DATE_FORMAT_YMDHMS;

@Builder
public record LimitResponseDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_YMDHMS)
        ZonedDateTime createdAt,

        BigDecimal amount,

        Currency currency
) {
}
