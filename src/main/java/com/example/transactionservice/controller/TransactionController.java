package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final LimitService limitService;

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.createTransaction(transactionRequestDto);
    }

    @PostMapping("/limits")
    @ResponseStatus(HttpStatus.CREATED)
    public LimitResponseDto createLimit(@Valid @RequestBody LimitRequestDto limitRequestDto) {
        return limitService.createLimit(limitRequestDto);
    }

    @GetMapping("/exceeded-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getExceededTransactions() {
        List<TransactionResponseDto> exceededTransactions = transactionService.getExceededTransactions();
        return ResponseEntity.ok(exceededTransactions);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
