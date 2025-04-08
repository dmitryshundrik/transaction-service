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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final ExchangeRateService exchangeRateService;

    private final LimitService limitService;

    @Override
    public void createTransaction(TransactionRequestDto transactionRequestDto) {
        Transaction transaction = transactionMapper.toTransaction(transactionRequestDto);
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(transaction.getCurrency());
        BigDecimal amountUsd = transaction.getAmount().multiply(exchangeRate.getRate());
        BigDecimal monthlyExpenses = getMonthlyExpenses(transaction.getExpenseCategory(), transaction.getCreatedAt())
                .add(amountUsd);
        Limit currentLimit = limitService.findCurrentLimit();
        boolean limitExceeded = currentLimit.getAmount().subtract(monthlyExpenses).compareTo(BigDecimal.ZERO) < 0;
        transaction.setLimit(currentLimit);
        transaction.setLimitExceeded(limitExceeded);
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionResponseDto> getExceededTransactions() {
        return transactionRepository.findByLimitExceeded(true)
                .stream()
                .map(transactionMapper::toTransactionResponseDto)
                .toList();
    }

    private BigDecimal getMonthlyExpenses(ExpenseCategory category, ZonedDateTime transactionDate) {
        ZonedDateTime startDate = transactionDate.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);
        return transactionRepository.findMonthlyTransactionExpenses(category, startDate, transactionDate);
    }
}
