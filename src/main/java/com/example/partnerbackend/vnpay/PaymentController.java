package com.example.partnerbackend.vnpay;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Payment", description = "Payment APIs")
@RequestMapping("/api/v1/payment")
public class PaymentController {

        private final VnpayService paymentService;

        @GetMapping("/create-payment")
        public ResponseEntity<CreatePaymentResponse> createPayment(CreatePaymentRequest request) throws UnsupportedEncodingException {
            return ResponseEntity.ok(paymentService.createPaymentUrl(request));
        }

        @GetMapping("/return-url")
        public ResponseEntity<String> returnUrl(@RequestParam String vnp_ResponseCode,
                                                @RequestParam String vnp_TxnRef,
                                                @RequestParam String vnp_TransactionNo,
                                                @RequestParam String vnp_Amount,
                                                @RequestParam String vnp_BankCode,
                                                @RequestParam String vnp_PayDate,
                                                @RequestParam String vnp_OrderInfo) {
            return ResponseEntity.ok("amount: " + vnp_Amount);

        }

}
