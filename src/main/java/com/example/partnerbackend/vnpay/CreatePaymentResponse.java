package com.example.partnerbackend.vnpay;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentResponse {
    private String status;
    private String message;
    private String URL;
}
