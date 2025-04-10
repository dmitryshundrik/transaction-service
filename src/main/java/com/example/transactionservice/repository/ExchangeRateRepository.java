package com.example.transactionservice.repository;

import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT e " +
            "FROM ExchangeRate e " +
            "WHERE e.currencyFrom = :currencyFrom " +
            "AND e.createdAt < :date " +
            "ORDER BY e.createdAt DESC LIMIT 1")
    Optional<ExchangeRate> findActualExchangeRate(@Param("currencyFrom") Currency currencyFrom,
                                                  @Param("date") ZonedDateTime date);
}
