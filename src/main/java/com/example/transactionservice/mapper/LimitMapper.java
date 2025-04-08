package com.example.transactionservice.mapper;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.entity.Limit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", imports = ZonedDateTime.class)
public interface LimitMapper {

    @Mapping(target = "createdAt", expression = "java(ZonedDateTime.now())")
    Limit toLimit(LimitRequestDto limitRequestDto);

    LimitResponseDto toLimitResponseDto(Limit limit);
}
