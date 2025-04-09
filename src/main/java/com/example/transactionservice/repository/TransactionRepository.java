package com.example.transactionservice.repository;

import com.example.transactionservice.model.entity.Transaction;
import com.example.transactionservice.model.enums.ExpenseCategory;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT COALESCE(SUM(t.amount * t.exchangeRate.rate), 0) " +
            "FROM Transaction t " +
            "WHERE t.expenseCategory = :expenseCategory " +
            "AND t.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal findMonthlyTransactionExpenses(@Param("expenseCategory") ExpenseCategory expenseCategory,
                                              @Param("startDate") ZonedDateTime startDate,
                                              @Param("endDate") ZonedDateTime endDate);

    List<Transaction> findByLimitExceeded(boolean limitExceeded);
}
