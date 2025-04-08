package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Schema(description = "DTO for creating a transaction")
public record TransactionRequestDto(
        @Schema(description = "The sender's bank account number", example = "0000000123")
        @Pattern(regexp = "\\d{10}", message = "The number must consist only of digits")
        @Size(min = 10, max = 10, message = "The number must contain exactly 10 digits")
        @JsonProperty("account_from")
        String accountFrom,

        @Schema(description = "The recipient's bank account number", example = "0000000123")
        @Pattern(regexp = "\\d{10}", message = "The number must consist only of digits")
        @Size(min = 10, max = 10, message = "The number must contain exactly 10 digits")
        @JsonProperty("account_to")
        String accountTo,

        @Schema(description = "The currency code of the transaction", example = "KZT")
        @JsonProperty("currency_shortname")
        Currency currency,

        @Schema(description = "The transaction amount", example = "550.00")
        @Positive
        @JsonProperty("sum")
        BigDecimal amount,

        @Schema(description = "The category of the expense (e.g., product or service)", example = "product")
        @JsonProperty("expense_category")
        ExpenseCategory expenseCategory
) {
}
