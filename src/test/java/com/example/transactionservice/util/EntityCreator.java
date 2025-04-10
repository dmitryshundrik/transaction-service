package com.example.transactionservice.util;

import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.entity.Limit;
import com.example.transactionservice.model.entity.Transaction;

import static com.example.transactionservice.util.TestConstants.ACCOUNT_FROM;
import static com.example.transactionservice.util.TestConstants.ACCOUNT_TO;
import static com.example.transactionservice.util.TestConstants.EXCHANGE_CURRENCY_KZT;
import static com.example.transactionservice.util.TestConstants.EXCHANGE_CURRENCY_USD;
import static com.example.transactionservice.util.TestConstants.EXCHANGE_RATE;
import static com.example.transactionservice.util.TestConstants.EXPENSE_CATEGORY_PRODUCT;
import static com.example.transactionservice.util.TestConstants.LIMIT_AMOUNT;
import static com.example.transactionservice.util.TestConstants.TRANSACTION_AMOUNT;

public abstract class EntityCreator {

    public static Transaction getTransaction() {
        return Transaction.builder()
                .accountFrom(ACCOUNT_FROM)
                .accountTo(ACCOUNT_TO)
                .amount(TRANSACTION_AMOUNT)
                .currency(EXCHANGE_CURRENCY_KZT)
                .expenseCategory(EXPENSE_CATEGORY_PRODUCT)
                .build();
    }

    public static ExchangeRate getExchangeRate() {
        return ExchangeRate.builder()
                .currencyFrom(EXCHANGE_CURRENCY_KZT)
                .currencyTo(EXCHANGE_CURRENCY_USD)
                .rate(EXCHANGE_RATE)
                .build();
    }

    public static Limit getLimit() {
        return Limit.builder()
                .amount(LIMIT_AMOUNT)
                .currency(EXCHANGE_CURRENCY_USD)
                .expenseCategory(EXPENSE_CATEGORY_PRODUCT)
                .build();
    }
}
