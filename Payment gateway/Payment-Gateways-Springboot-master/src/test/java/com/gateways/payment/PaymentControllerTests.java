package com.gateways.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateways.request.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPay_Success() throws Exception {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 100.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("WOMPI")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        when(paymentService.pay(any(TransactionRequest.class))).thenReturn("Payment successful");

        // Act & Assert
        mockMvc.perform(post("/gateways/v1/api/payments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment successful"));
    }

    @Test
    void testPay_Failure() throws Exception {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 100.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("WOMPI")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        when(paymentService.pay(any(TransactionRequest.class))).thenReturn("Payment failed");

        // Act & Assert
        mockMvc.perform(post("/gateways/v1/api/payments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment failed"));
    }

    @Test
    void testPay_InvalidRequest_BadRequest() throws Exception {
        // Arrange
        String invalidJson = "{\"gateway\": \"\", \"method\": \"CARD\"}";

        // Act & Assert
        mockMvc.perform(post("/gateways/v1/api/payments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
