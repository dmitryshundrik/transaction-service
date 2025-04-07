package com.example.transactionservice.service.impl;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        //todo
        return null;
    }
}
