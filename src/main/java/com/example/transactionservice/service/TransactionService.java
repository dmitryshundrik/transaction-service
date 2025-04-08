package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import java.util.List;

public interface TransactionService {

    void createTransaction(TransactionRequestDto transactionRequestDto);

    List<TransactionResponseDto> getExceededTransactions();

}
