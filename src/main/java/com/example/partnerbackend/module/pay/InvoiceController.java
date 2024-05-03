package com.example.partnerbackend.module.pay;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/invoice")
@Tag(name = "Invoice", description = "Invoice APIs")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<Page<Invoice>> getInvoices(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(invoiceService.getInvoices(page, size));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Invoice> getInvoiceById(@RequestParam Long id) {
        return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteInvoice(@RequestParam Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/generate-invoice")
    public ResponseEntity<Invoice> generateInvoice(@RequestParam Long partnerId, @RequestBody List<Long> serviceUsageIds) {
        System.out.println("12345"+serviceUsageIds);
        return ResponseEntity.ok().body(invoiceService.generateInvoice(partnerId, serviceUsageIds));
    }
}
