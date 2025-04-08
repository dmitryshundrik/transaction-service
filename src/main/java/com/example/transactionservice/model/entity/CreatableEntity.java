package com.example.transactionservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class CreatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;
}