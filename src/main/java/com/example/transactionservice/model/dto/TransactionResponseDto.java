package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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
        ZonedDateTime createdAt,

        @JsonProperty("limit_sum")
        BigDecimal limitSum,

        @JsonProperty("limit_datetime")
        ZonedDateTime limitDatetime,

        @JsonProperty("limit_currency_shortname")
        String limitCurrencyShortname
) {
}
