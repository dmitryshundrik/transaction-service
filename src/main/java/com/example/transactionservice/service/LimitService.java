package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;

public interface LimitService {

    Limit findCurrentLimit();

    LimitResponseDto createLimit(LimitRequestDto limitRequestDto);

}
