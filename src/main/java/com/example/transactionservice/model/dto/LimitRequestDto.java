package com.example.transactionservice.model.dto;

import com.example.transactionservice.model.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO for requesting a limit setup.
 */
public record LimitRequestDto(
        @NotNull(message = "Limit amount cannot be null")
        @Positive(message = "Limit amount must be positive")
        BigDecimal amount,

        @NotBlank(message = "Currency code cannot be empty")
        @Size(min = 3, max = 3, message = "Currency code must be exactly 3 characters long")
        Currency currency
) {
}
