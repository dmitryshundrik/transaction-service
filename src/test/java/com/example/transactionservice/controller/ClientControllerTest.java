package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.LimitRequestDto;
import com.example.transactionservice.model.dto.LimitResponseDto;
import com.example.transactionservice.model.dto.TransactionResponseDto;
import com.example.transactionservice.service.LimitService;
import com.example.transactionservice.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import static com.example.transactionservice.util.DtoCreator.getLimitRequestDto;
import static com.example.transactionservice.util.DtoCreator.getLimitResponseDto;
import static com.example.transactionservice.util.DtoCreator.getTransactionResponseDto;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private LimitService limitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetExceededTransactions_Success() throws Exception {
        TransactionResponseDto responseDto = getTransactionResponseDto();
        List<TransactionResponseDto> responseDtoList = List.of(responseDto);

        Mockito.when(transactionService.getExceededTransactions()).thenReturn(responseDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/exceeded-transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].account_from").value("0000000001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sum").value(100.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].limit_sum").value(1000.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency_shortname").value("KZT"));
    }

    @Test
    void testGetLimits_Success() throws Exception {
        LimitResponseDto responseDto = getLimitResponseDto();
        List<LimitResponseDto> responseDtoList = List.of(responseDto);

        Mockito.when(limitService.getLimits()).thenReturn(responseDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/limits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].createdAt").value("2025-01-01 12:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(1000.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency").value("USD"));
    }

    @Test
    void testCreateLimit_Success() throws Exception {
        LimitRequestDto requestDto = getLimitRequestDto();
        LimitResponseDto responseDto = getLimitResponseDto();

        Mockito.when(limitService.createLimit(any(LimitRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/client/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value("2025-01-01 12:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(1000.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("USD"));
    }

    @Test
    void testCreateLimit_InvalidAmount() throws Exception {
        String invalidJson = """
                {
                    "amount": null,
                    "currency": "KZT",
                    "expenseCategory": "PRODUCT"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/client/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateLimit_NegativeAmount() throws Exception {
        String invalidJson = """
                {
                    "amount": -100.00,
                    "currency": "USD",
                    "expenseCategory": "SERVICE"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/client/limits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}