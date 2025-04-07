package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransactionResponseDto {

    private String accountFrom;

    private String accountTo;

    private Currency currency;

    private BigDecimal amount;

    private ExpenseCategory expenseCategory;

    private BigDecimal limit_sum;

    private ZonedDateTime limitDatetime;

    private String limitCurrencyShortname;
}
