package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TransactionResponseDto(
        String accountFrom,

        String accountTo,

        Currency currency,

        BigDecimal amount,

        ExpenseCategory expenseCategory,

        BigDecimal limitSum,

        ZonedDateTime limitDatetime,

        String limitCurrencyShortname
) {
}
