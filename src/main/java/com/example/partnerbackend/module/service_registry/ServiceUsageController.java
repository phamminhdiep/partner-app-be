package com.example.partnerbackend.module.service_registry;

import com.example.partnerbackend.dto.ServiceUsageDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/service-usage")
@Tag(name = "Service Usage", description = "Service Usage APIs")
public class ServiceUsageController {
    private final ServiceUsageService serviceUsageService;

    @Autowired
    public ServiceUsageController(ServiceUsageService serviceUsageService) {
        this.serviceUsageService = serviceUsageService;
    }
    @GetMapping
    public ResponseEntity<Page<ServiceUsage>> getServiceUsages(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(serviceUsageService.getServiceUsages(page, size));
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<ServiceUsage> getServiceUsageById(@RequestParam Long id) {
        return ResponseEntity.ok(serviceUsageService.getServiceUsageById(id));
    }

    @GetMapping("/get-by-partner-id")
    public ResponseEntity<List<ServiceUsageDto>> getServiceUsageByPartnerId(@RequestParam Long partnerId) {
        return ResponseEntity.ok(serviceUsageService.findByPartnerId(partnerId));
    }

    @GetMapping("/get-by-invoice-id")
    public ResponseEntity<List<ServiceUsageDto>> getServiceUsageByInvoiceId(@RequestParam Long invoiceId) {
        return ResponseEntity.ok(serviceUsageService.findByInvoiceId(invoiceId));
    }

    @PostMapping
    public ResponseEntity<ServiceUsage> saveServiceUsage(@RequestBody ServiceUsage serviceUsage) {
        return ResponseEntity.ok(serviceUsageService.saveServiceUsage(serviceUsage));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteServiceUsage(@RequestParam Long id) {
        serviceUsageService.deleteServiceUsage(id);
        return ResponseEntity.ok().build();
    }
}
