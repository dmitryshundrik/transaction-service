package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Client API", description = "API for managing client transactions and limits")
public class ClientController {

    private final TransactionService transactionService;

    private final LimitService limitService;

    @Operation(
            summary = "Get list of exceeded transactions",
            description = "Returns a list of all transactions that exceed the set limits."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponseDto.class)
                    )
            )
    })
    @GetMapping("/exceeded-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getExceededTransactions() {
        List<TransactionResponseDto> exceededTransactions = transactionService.getExceededTransactions();
        return ResponseEntity.ok(exceededTransactions);
    }

    @Operation(
            summary = "Get list of limits",
            description = "Returns a list of all set limits."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of limits retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LimitResponseDto.class)
                    )
            )
    })
    @GetMapping("/limits")
    public List<LimitResponseDto> getLimits() {
        return limitService.getLimits();
    }

    @Operation(
            summary = "Create a new limit",
            description = "Creates a new limit based on the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Limit created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LimitResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content
            )
    })
    @PostMapping("/limits")
    @ResponseStatus(HttpStatus.CREATED)
    public LimitResponseDto createLimit(@Valid @RequestBody LimitRequestDto limitRequestDto) {
        return limitService.createLimit(limitRequestDto);
    }
}
