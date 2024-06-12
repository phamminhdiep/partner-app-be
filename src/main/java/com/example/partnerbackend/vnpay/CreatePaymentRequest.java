package com.example.partnerbackend.vnpay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentRequest {
    private Long invoiceId;
    private String paymentMethod;
    private Double amount;
    private String description;
}
