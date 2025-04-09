package com.example.transactionservice.service.impl;

import com.example.transactionservice.exception.LimitNotFoundException;
import com.example.transactionservice.mapper.LimitMapper;
import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;
import com.example.transactionservice.repository.LimitRepository;
import com.example.transactionservice.service.LimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

    private final LimitMapper limitMapper;

    @Override
    @Transactional(readOnly = true)
    public Limit getCurrentLimit() {
        return limitRepository.findTopByOrderByCreatedAtDesc()
                .orElseThrow(() -> {
                    log.error("No limit found in the database");
                    return new LimitNotFoundException("No limit found");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<LimitResponseDto> getLimits() {
        return limitRepository.findAll().stream()
                .map(limitMapper::toLimitResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public LimitResponseDto createLimit(LimitRequestDto limitRequestDto) {
        Limit limit = limitMapper.toLimit(limitRequestDto);
        Limit savedLimit = limitRepository.save(limit);
        log.debug("Limit created with ID: {}", savedLimit.getId());
        return limitMapper.toLimitResponseDto(savedLimit);
    }
}
