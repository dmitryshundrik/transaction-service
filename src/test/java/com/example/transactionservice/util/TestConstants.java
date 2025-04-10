package com.example.transactionservice.util;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public abstract class TestConstants {

    public static final String ACCOUNT_FROM = "0000000001";

    public static final String ACCOUNT_TO = "0000000002";

    public static final Currency EXCHANGE_CURRENCY_USD = Currency.USD;

    public static final Currency EXCHANGE_CURRENCY_KZT = Currency.KZT;

    public static final BigDecimal TRANSACTION_AMOUNT = BigDecimal.valueOf(100.00);

    public static final ExpenseCategory EXPENSE_CATEGORY_PRODUCT = ExpenseCategory.PRODUCT;

    public static final ZonedDateTime TRANSACTION_CREATED_AT = ZonedDateTime.parse("2025-01-10T12:00:00Z");

    public static final BigDecimal LIMIT_AMOUNT = BigDecimal.valueOf(1000.00);

    public static final ZonedDateTime LIMIT_CREATED_AT = ZonedDateTime.parse("2025-01-01T12:00:00Z");

    public static final BigDecimal EXCHANGE_RATE = BigDecimal.valueOf(1.00);

    public static final BigDecimal MONTHLY_TRANSACTION_EXPENSES = BigDecimal.valueOf(1000.00);

}
