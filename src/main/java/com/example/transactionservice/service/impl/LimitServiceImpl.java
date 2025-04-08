package com.example.transactionservice.service.impl;

import com.example.transactionservice.mapper.LimitMapper;
import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;
import com.example.transactionservice.repository.LimitRepository;
import com.example.transactionservice.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

    private final LimitMapper limitMapper;

    @Override
    public Limit findCurrentLimit() {
        return limitRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> new RuntimeException("No limit found"));
    }

    @Override
    public LimitResponseDto createLimit(LimitRequestDto limitRequestDto) {
        Limit limit = limitMapper.toLimit(limitRequestDto);
        return limitMapper.toLimitResponseDto(limitRepository.save(limit));
    }
}
