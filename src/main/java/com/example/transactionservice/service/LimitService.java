package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;

public interface LimitService {

    LimitResponseDto createLimit(LimitRequestDto limitRequestDto);

}
