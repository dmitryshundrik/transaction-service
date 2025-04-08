package com.example.transactionservice.model.entity;

import com.example.transactionservice.model.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "exchange_rates")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate extends CreatableEntity {

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "currency_from", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyFrom;

    @Column(name = "currency_to", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
