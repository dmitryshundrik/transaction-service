package com.example.transactionservice.util;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;

import static com.example.transactionservice.util.TestConstants.ACCOUNT_FROM;
import static com.example.transactionservice.util.TestConstants.ACCOUNT_TO;
import static com.example.transactionservice.util.TestConstants.TRANSACTION_CREATED_AT;
import static com.example.transactionservice.util.TestConstants.EXCHANGE_CURRENCY_KZT;
import static com.example.transactionservice.util.TestConstants.EXCHANGE_CURRENCY_USD;
import static com.example.transactionservice.util.TestConstants.EXPENSE_CATEGORY_PRODUCT;
import static com.example.transactionservice.util.TestConstants.LIMIT_CREATED_AT;
import static com.example.transactionservice.util.TestConstants.LIMIT_AMOUNT;
import static com.example.transactionservice.util.TestConstants.TRANSACTION_AMOUNT;

public class DtoCreator {

    public static TransactionResponseDto getTransactionResponseDto() {
        return TransactionResponseDto.builder()
                .accountFrom(ACCOUNT_FROM)
                .accountTo(ACCOUNT_TO)
                .currency(EXCHANGE_CURRENCY_KZT)
                .amount(TRANSACTION_AMOUNT)
                .expenseCategory(EXPENSE_CATEGORY_PRODUCT)
                .createdAt(TRANSACTION_CREATED_AT)
                .limitSum(LIMIT_AMOUNT)
                .limitCreatedAt(LIMIT_CREATED_AT)
                .limitCurrencyShortname(EXCHANGE_CURRENCY_USD.name())
                .build();
    }

    public static TransactionRequestDto getTransactionRequestDto() {
        return TransactionRequestDto.builder()
                .accountFrom(ACCOUNT_FROM)
                .accountTo(ACCOUNT_TO)
                .currency(EXCHANGE_CURRENCY_KZT)
                .amount(TRANSACTION_AMOUNT)
                .expenseCategory(EXPENSE_CATEGORY_PRODUCT)
                .createdAt(TRANSACTION_CREATED_AT)
                .build();
    }

    public static LimitResponseDto getLimitResponseDto() {
        return LimitResponseDto.builder()
                .createdAt(LIMIT_CREATED_AT)
                .amount(LIMIT_AMOUNT)
                .currency(EXCHANGE_CURRENCY_USD)
                .build();
    }

    public static LimitRequestDto getLimitRequestDto() {
        return LimitRequestDto.builder()
                .amount(LIMIT_AMOUNT)
                .currency(EXCHANGE_CURRENCY_USD)
                .expenseCategory(EXPENSE_CATEGORY_PRODUCT)
                .build();
    }
}
