package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import java.util.List;

/**
 * Service interface for managing bank transactions.
 * Provides methods to process and handle transaction-related operations.
 */
public interface TransactionService {

    /**
     * Creates a new transaction based on the provided transaction request data.
     *
     * @param transactionRequestDto the data transfer object containing transaction details such as
     *                              account numbers, amount, currency, category, and timestamp.
     *                              Must not be null and should contain valid data as per the system's requirements.
     */
    void createTransaction(TransactionRequestDto transactionRequestDto);

    /**
     * Retrieves a list of transactions that have exceeded the defined limits.
     *
     * @return a list of {@link TransactionResponseDto} objects containing details
     *         of transactions that exceeded limits
     */
    List<TransactionResponseDto> getExceededTransactions();

}
