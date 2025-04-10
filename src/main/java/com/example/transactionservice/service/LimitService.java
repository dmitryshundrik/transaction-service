package com.example.transactionservice.service;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;
import java.util.List;

/**
 * Service interface for managing limits.
 * Allows retrieval of the current limit, creation of new limits, and fetching a list of all limits.
 */
public interface LimitService {

    /**
     * Retrieves the currently active limit.
     *
     * @return a {@link Limit} object representing the current limit
     */
    Limit getCurrentLimit();

    /**
     * Creates a new limit based on the provided data.
     *
     * @param limitRequestDto the request object containing limit details,
     *                        such as amount, currency, and validity period
     * @return a {@link LimitResponseDto} object with information about the created limit
     */
    LimitResponseDto createLimit(LimitRequestDto limitRequestDto);

    /**
     * Retrieves a list of all established limits.
     *
     * @return a list of {@link LimitResponseDto} objects containing details of all existing limits
     */
    List<LimitResponseDto> getLimits();

}
