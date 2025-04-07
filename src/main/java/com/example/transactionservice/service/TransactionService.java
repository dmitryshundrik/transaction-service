package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);
}
