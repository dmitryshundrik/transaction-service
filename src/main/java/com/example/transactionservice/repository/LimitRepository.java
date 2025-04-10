package com.example.transactionservice.repository;

import com.example.transactionservice.model.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findTopByOrderByCreatedAtDesc();
}
