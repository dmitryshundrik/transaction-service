package com.example.transactionservice.mapper;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", imports = ZonedDateTime.class)
public interface TransactionMapper {

    Transaction toTransaction(TransactionRequestDto transactionRequestDto);

    @Mapping(target = "limitSum", source = "limit.amount")
    @Mapping(target = "limitCreatedAt", source = "limit.createdAt")
    @Mapping(target = "limitCurrencyShortname", constant = "USD")
    TransactionResponseDto toTransactionResponseDto(Transaction transaction);
}
