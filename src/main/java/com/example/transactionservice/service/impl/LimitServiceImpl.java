package com.example.transactionservice.service.impl;

import com.example.transactionservice.repository.LimitRepository;
import com.example.transactionservice.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

}
