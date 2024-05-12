package com.example.partnerbackend.vnpay;

import lombok.Data;

@Data
public class CreatePaymentRequest {
    private Long invoiceId;
    private String paymentMethod;
    private Double amount;
    private String description;
}
