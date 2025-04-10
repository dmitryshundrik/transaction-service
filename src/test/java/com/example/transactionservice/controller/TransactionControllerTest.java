package com.example.transactionservice.controller;

import com.example.transactionservice.model.dto.TransactionRequestDto;
import com.example.transactionservice.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testCreateTransaction_Success() throws Exception {
        String transactionJson = """
                {
                    "account_from" : "0000000001",
                    "account_to" : "0000000002",
                    "currency_shortname": "KZT",
                    "sum": "100.50",
                    "expense_category" : "PRODUCT",
                    "datetime": "2025-01-01 12:00:00+06"
                }
                """;

        Mockito.doNothing().when(transactionService).createTransaction(any(TransactionRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateTransaction_InvalidEmptyRequest() throws Exception {
        String invalidJson = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateTransaction_InvalidNegativeAmount() throws Exception {
        String invalidJson = """
                {
                    "account_from" : "0000000001",
                    "account_to" : "0000000002",
                    "currency_shortname": "KZT",
                    "sum": "-100.50",
                    "expense_category" : "PRODUCT",
                    "datetime": "2025-01-01 12:00:00+06"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateTransaction_InvalidCurrency() throws Exception {
        String invalidJson = """
                {
                    "account_from" : "0000000001",
                    "account_to" : "0000000002",
                    "currency_shortname": "ABC",
                    "sum": "100.50",
                    "expense_category" : "PRODUCT",
                    "datetime": "2025-01-01 12:00:00+06"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateTransaction_ServiceInvocation() throws Exception {
        String transactionJson = """
                {
                    "account_from" : "0000000123",
                    "account_to" : "9999999999",
                    "currency_shortname": "KZT",
                    "sum": "100.50",
                    "expense_category" : "PRODUCT",
                    "datetime": "2025-01-01 12:00:00+06"
                }
                """;

        Mockito.doNothing().when(transactionService).createTransaction(any(TransactionRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isCreated());

        Mockito.verify(transactionService, Mockito.times(1))
                .createTransaction(any(TransactionRequestDto.class));
    }
}