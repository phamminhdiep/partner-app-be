package com.example.partnerbackend.vnpay;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
public class ReturnUrlResponse {
    private String vnp_ResponseCode;
    private String vnp_TxnRef;
    private String vnp_TransactionNo;
    private String vnp_Amount;
    private String vnp_BankCode;
    private String vnp_PayDate;
    private String vnp_OrderInfo;
}
