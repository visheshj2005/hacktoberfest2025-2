package com.gateways.payment;

import com.gateways.request.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTests {

    private final PaymentService paymentService = new PaymentService();

    @Test
    void testPayWithWompiCard_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 100.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("WOMPI")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithWompiNequi_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 50.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("WOMPI")
                .method("NEQUI")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithStrapiCard_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 200.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("STRAPI")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithStrapiZelle_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 75.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("STRAPI")
                .method("ZELLE")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithPaypalCard_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 150.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("PAYPAL")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithPaypalApplePay_Success() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        paymentData.put("amount", 300.0);
        TransactionRequest request = TransactionRequest.builder()
                .gateway("PAYPAL")
                .method("APPLE_PAY")
                .paymentData(paymentData)
                .build();

        // Act
        String result = paymentService.pay(request);

        // Assert
        assertEquals("Payment successful", result);
    }

    @Test
    void testPayWithUnknownGateway_ThrowsException() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        TransactionRequest request = TransactionRequest.builder()
                .gateway("UNKNOWN")
                .method("CARD")
                .paymentData(paymentData)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.pay(request);
        });
        assertEquals("Unknown payment gateway: UNKNOWN", exception.getMessage());
    }

    @Test
    void testPayWithUnknownMethod_ThrowsException() {
        // Arrange
        Map<String, Object> paymentData = new HashMap<>();
        TransactionRequest request = TransactionRequest.builder()
                .gateway("WOMPI")
                .method("UNKNOWN")
                .paymentData(paymentData)
                .build();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.pay(request);
        });
        assertEquals("Unknown transaction method: UNKNOWN", exception.getMessage());
    }
}
