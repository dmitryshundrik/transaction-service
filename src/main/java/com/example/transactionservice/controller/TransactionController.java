package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final LimitService limitService;

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        //todo
    }

    @PostMapping("/limits")
    public ResponseEntity<LimitResponseDto> setLimit(@RequestBody LimitRequestDto limitRequestDto) {
        return null;
    }

    @GetMapping("/exceeded-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getExceededTransactions() {
        return null;
    }
}
