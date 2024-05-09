package com.example.partnerbackend.controller;

import com.example.partnerbackend.entity.Services;
import com.example.partnerbackend.service.ServicesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/services")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Services", description = "Services APIs")
public class ServicesController {
    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping
    public ResponseEntity<Page<Services>> getServices(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(servicesService.getServices(page, size));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Services> getServiceById(@RequestParam Long id) {
        return ResponseEntity.ok(servicesService.getServiceById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Services>> getServices() {
        return ResponseEntity.ok(servicesService.getAllServices());
    }

    @PostMapping
    public ResponseEntity<Services> saveService(@RequestBody Services service) {
        return ResponseEntity.ok(servicesService.saveService(service));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteService(@RequestParam Long id) {
        servicesService.deleteService(id);
        return ResponseEntity.ok().build();
    }

}
