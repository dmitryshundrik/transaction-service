package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A record representing a request to create a bank transaction.
 */
@Schema(description = "DTO for creating a transaction")
public record TransactionRequestDto(
        @Schema(description = "The sender's bank account number", example = "0000000123")
        @Pattern(regexp = "\\d{10}", message = "The number must consist only of digits")
        @Size(min = 10, max = 10, message = "The number must contain exactly 10 digits")
        String accountFrom,

        @Schema(description = "The recipient's bank account number", example = "0000000123")
        @Pattern(regexp = "\\d{10}", message = "The number must consist only of digits")
        @Size(min = 10, max = 10, message = "The number must contain exactly 10 digits")
        String accountTo,

        @Schema(description = "The currency code of the transaction", example = "KZT")
        Currency currencyShortname,

        @Schema(description = "The transaction amount", example = "550.00")
        @Min(value = 1, message = "Invalid transaction amount")
        @JsonProperty("sum")
        BigDecimal amount,

        @Schema(description = "The category of the expense (e.g., product or service)", example = "product")
        @JsonProperty("expense_category")
        ExpenseCategory expenseCategory
) {
}
