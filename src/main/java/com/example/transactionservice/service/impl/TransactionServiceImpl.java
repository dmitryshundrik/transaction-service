package com.example.transactionservice.service.impl;

import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.entity.Limit;
import com.example.transactionservice.model.entity.Transaction;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.ExchangeRateService;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final ExchangeRateService exchangeRateService;

    private final LimitService limitService;

    @Override
    @Transactional
    public void createTransaction(TransactionRequestDto transactionRequestDto) {
        log.info("Creating transaction for account: {} to {}", transactionRequestDto.accountFrom(), transactionRequestDto.accountTo());
        Transaction transaction = transactionMapper.toTransaction(transactionRequestDto);
        log.debug("Fetching exchange rate for currency: {}", transaction.getCurrency());
        ExchangeRate exchangeRate = exchangeRateService
                .getActualExchangeRate(transaction.getCurrency(), transaction.getCreatedAt());
        BigDecimal amountUsd = transaction.getAmount().multiply(exchangeRate.getRate());
        log.debug("Calculated amount in USD: {}", amountUsd);
        BigDecimal monthlyExpenses = getMonthlyExpenses(transaction.getExpenseCategory(), transaction.getCreatedAt()).add(amountUsd);
        Limit currentLimit = limitService.getCurrentLimit();
        boolean limitExceeded = currentLimit.getAmount().subtract(monthlyExpenses).compareTo(BigDecimal.ZERO) < 0;
        transaction.setLimit(currentLimit);
        transaction.setLimitExceeded(limitExceeded);
        transaction.setExchangeRate(exchangeRate);
        transaction = transactionRepository.save(transaction);
        log.info("Transaction created with ID: {}", transaction.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> getExceededTransactions() {
        return transactionRepository.findByLimitExceeded(true).stream()
                .map(transactionMapper::toTransactionResponseDto)
                .toList();
    }

    private BigDecimal getMonthlyExpenses(ExpenseCategory category, ZonedDateTime transactionDate) {
        ZonedDateTime startDate = transactionDate.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
        log.debug("Calculating monthly expenses for category {} from {} to {}", category, startDate, transactionDate);
        BigDecimal expenses = transactionRepository.findMonthlyTransactionExpenses(category, startDate, transactionDate);
        log.debug("Monthly expenses calculated: {}", expenses);
        return expenses;
    }
}
