package com.example.transactionservice.model.entity;

import com.example.transactionservice.model.enums.Currency;
import com.example.transactionservice.model.enums.ExpenseCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Entity
@Table(name = "limits")
@Data
@EqualsAndHashCode(callSuper = true)
public class Limit extends CreatableEntity {

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "expense_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;
}
