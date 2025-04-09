package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final TransactionService transactionService;

    private final LimitService limitService;

    @GetMapping("/exceeded-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getExceededTransactions() {
        List<TransactionResponseDto> exceededTransactions = transactionService.getExceededTransactions();
        return ResponseEntity.ok(exceededTransactions);
    }

    @GetMapping("/limits")
    public List<LimitResponseDto> getLimits() {
        return limitService.getLimits();
    }

    @PostMapping("/limits")
    @ResponseStatus(HttpStatus.CREATED)
    public LimitResponseDto createLimit(@Valid @RequestBody LimitRequestDto limitRequestDto) {
        return limitService.createLimit(limitRequestDto);
    }
}
