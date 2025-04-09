package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;
import java.util.List;

public interface LimitService {

    Limit getCurrentLimit();

    LimitResponseDto createLimit(LimitRequestDto limitRequestDto);

    List<LimitResponseDto> getLimits();

}
