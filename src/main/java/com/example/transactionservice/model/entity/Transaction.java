package com.example.transactionservice.model.entity;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "transactions")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "account_from", nullable = false)
    private String accountFrom;

    @Column(name = "account_to", nullable = false)
    private String accountTo;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @ManyToOne
    @JoinColumn(name = "limit_id")
    private Limit limit;

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;

    @Column(name = "amount_exceeded")
    private BigDecimal amountExceeded;
}