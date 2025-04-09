package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Schema(description = "DTO for creating a limit")
public record LimitRequestDto(
        @Schema(description = "The limit amount", example = "1000.00")
        @NotNull(message = "Limit amount cannot be null")
        @Positive(message = "Limit amount must be positive")
        BigDecimal amount,

        @Schema(description = "The currency code of the limit", example = "KZT")
        @NotNull(message = "Currency code cannot be null")
        Currency currency,

        @Schema(description = "The category of the expense (e.g., product or service)", example = "PRODUCT")
        @NotNull(message = "Expense category cannot be null")
        ExpenseCategory expenseCategory
) {
}
