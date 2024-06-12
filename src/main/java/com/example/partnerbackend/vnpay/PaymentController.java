package com.example.partnerbackend.vnpay;

import com.example.partnerbackend.entity.Invoice;
import com.example.partnerbackend.entity.ServiceUsage;
import com.example.partnerbackend.service.InvoiceService;
import com.example.partnerbackend.service.ServiceUsageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@Tag(name = "Payment", description = "Payment APIs")
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentController {


        private final ServiceUsageService serviceUsageService;
        private final VnpayService paymentService;
        private final InvoiceService invoiceService;

        @GetMapping("/create-payment")
        public ResponseEntity<CreatePaymentResponse> createPayment(@RequestParam Double amount, @RequestParam String serviceUsedList) throws UnsupportedEncodingException {
            System.out.println("amount: " + amount);
            System.out.println("serviceUsedList: " + serviceUsedList);
            return ResponseEntity.ok(paymentService.createPaymentUrl(amount,serviceUsedList));
        }

        @GetMapping("/return-url")
        public ResponseEntity<?> returnUrl(
                HttpServletResponse response,
                @RequestParam String vnp_ResponseCode,
                @RequestParam String vnp_TxnRef,
                @RequestParam String vnp_TransactionNo,
                @RequestParam String vnp_Amount,
                @RequestParam String vnp_BankCode,
                @RequestParam String vnp_PayDate,
                @RequestParam String vnp_OrderInfo

        ) throws IOException {

            ReturnUrlResponse dto = ReturnUrlResponse.builder()
                    .vnp_ResponseCode(vnp_ResponseCode)
                    .vnp_TxnRef(vnp_TxnRef)
                    .vnp_TransactionNo(vnp_TransactionNo)
                    .vnp_Amount(vnp_Amount)
                    .vnp_BankCode(vnp_BankCode)
                    .vnp_PayDate(vnp_PayDate)
                    .vnp_OrderInfo(vnp_OrderInfo)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonDto = objectMapper.writeValueAsString(dto);

            String [] serviceUsedList = vnp_OrderInfo.split(",");

            Invoice invoice = invoiceService.saveInvoice(Invoice.builder()
                    .total(Double.parseDouble(vnp_Amount)/100)
                    .build());

            for (String serviceUsedId : serviceUsedList) {
                ServiceUsage serviceUsage = serviceUsageService.getServiceUsageById(Long.parseLong(serviceUsedId));
                serviceUsage.setStatus(true);
                serviceUsage.setInvoice(invoice);
                serviceUsageService.saveServiceUsage(serviceUsage);
            }

            response.sendRedirect("http://localhost:3000/success");

            return ResponseEntity.ok(jsonDto);

        }

}
