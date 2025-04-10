package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.example.transactionservice.util.Constants.DATE_FORMAT_YMDHMS;

@Builder
public record TransactionResponseDto(
        @JsonProperty("account_from")
        String accountFrom,

        @JsonProperty("account_to")
        String accountTo,

        @JsonProperty("currency_shortname")
        Currency currency,

        @JsonProperty("sum")
        BigDecimal amount,

        @JsonProperty("expense_category")
        ExpenseCategory expenseCategory,

        @JsonProperty("datetime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_YMDHMS)
        ZonedDateTime createdAt,

        @JsonProperty("limit_sum")
        BigDecimal limitSum,

        @JsonProperty("limit_datetime")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_YMDHMS)
        ZonedDateTime limitCreatedAt,

        @JsonProperty("limit_currency_shortname")
        String limitCurrencyShortname
) {
}
