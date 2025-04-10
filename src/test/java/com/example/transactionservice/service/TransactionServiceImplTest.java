package com.example.transactionservice.service;

import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.entity.Limit;
import com.example.transactionservice.model.entity.Transaction;
import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.transactionservice.util.DtoCreator.getTransactionRequestDto;
import static com.example.transactionservice.util.EntityCreator.getExchangeRate;
import static com.example.transactionservice.util.EntityCreator.getLimit;
import static com.example.transactionservice.util.EntityCreator.getTransaction;
import static com.example.transactionservice.util.TestConstants.MONTHLY_TRANSACTION_EXPENSES;
import static com.example.transactionservice.util.TestConstants.TRANSACTION_CREATED_AT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private LimitService limitService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private ZonedDateTime transactionCreatedAt;

    private TransactionRequestDto requestDto;

    private ExchangeRate exchangeRate;


    @BeforeEach
    void setUp() {
        transactionCreatedAt = TRANSACTION_CREATED_AT;

        Transaction transaction = getTransaction();
        transaction.setCreatedAt(transactionCreatedAt);

        requestDto = getTransactionRequestDto();

        exchangeRate = getExchangeRate();

        Limit limit = getLimit();

        when(transactionMapper.toTransaction(requestDto)).thenReturn(transaction);
        when(exchangeRateService.getActualExchangeRate(any(), any())).thenReturn(exchangeRate);
        when(limitService.getCurrentLimit()).thenReturn(limit);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
    }

    @Test
    void createTransaction_SetsLimitExceededFalse() {
        when(transactionRepository.findMonthlyTransactionExpenses(any(), any(), any()))
                .thenReturn(BigDecimal.ZERO);

        transactionService.createTransaction(requestDto);

        verify(transactionRepository).save(argThat(t -> !t.isLimitExceeded()));
        verify(exchangeRateService).getActualExchangeRate(Currency.KZT, transactionCreatedAt);
        verify(limitService).getCurrentLimit();
    }

    @Test
    void createTransaction_SetsLimitExceededTrue() {
        when(transactionRepository.findMonthlyTransactionExpenses(any(), any(), any()))
                .thenReturn(MONTHLY_TRANSACTION_EXPENSES);

        transactionService.createTransaction(requestDto);

        verify(transactionRepository).save(argThat(Transaction::isLimitExceeded));
    }

    @Test
    void createTransaction_AdjustsAmountUsdCorrectly() {
        exchangeRate.setRate(new BigDecimal("2.00"));
        when(exchangeRateService.getActualExchangeRate(any(), any()))
                .thenReturn(exchangeRate);
        when(transactionRepository.findMonthlyTransactionExpenses(any(), any(), any()))
                .thenReturn(MONTHLY_TRANSACTION_EXPENSES);

        transactionService.createTransaction(requestDto);

        verify(transactionRepository).save(argThat(Transaction::isLimitExceeded));
    }

    @Test
    void createTransaction_MonthlyExpensesCalledWithCorrectDates() {
        when(transactionRepository.findMonthlyTransactionExpenses(any(), any(), any()))
                .thenReturn(BigDecimal.ZERO);

        transactionService.createTransaction(requestDto);

        ZonedDateTime expectedStartDate = transactionCreatedAt.withDayOfMonth(1).truncatedTo(ChronoUnit.DAYS);

        verify(transactionRepository)
                .findMonthlyTransactionExpenses(ExpenseCategory.PRODUCT, expectedStartDate, transactionCreatedAt);
    }
}