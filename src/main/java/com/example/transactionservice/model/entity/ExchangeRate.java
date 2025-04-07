package com.example.transactionservice.model.entity;

import com.example.transactionservice.model.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "exchange_rates")
@Data
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "currency_from", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyFrom;

    @Column(name = "currency_to", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;

    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
