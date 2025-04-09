package com.example.transactionservice.repository;

import com.example.transactionservice.model.entity.ExchangeRate;
import com.example.transactionservice.model.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findTopByCurrencyFromOrderByCreatedAtDesc(Currency currencyFrom);

}
