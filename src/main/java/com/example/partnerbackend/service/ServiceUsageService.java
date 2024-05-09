package com.example.partnerbackend.service;

import com.example.partnerbackend.dto.ServiceUsageDto;
import com.example.partnerbackend.entity.ServiceUsage;
import com.example.partnerbackend.entity.Services;
import com.example.partnerbackend.repository.ServiceUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsageService {
    private final ServiceUsageRepository serviceUsageRepository;
    private final ServicesService servicesService;
    @Autowired
    public ServiceUsageService(ServiceUsageRepository serviceUsageRepository, ServicesService servicesService) {
        this.serviceUsageRepository = serviceUsageRepository;
        this.servicesService = servicesService;
    }

    public Page<ServiceUsage> getServiceUsages(int page, int size) {
        return serviceUsageRepository.findAll(PageRequest.of(page - 1, size));
    }

    public ServiceUsage getServiceUsageById(Long id) {
        return serviceUsageRepository.findById(id).orElse(null);
    }
    public ServiceUsage saveServiceUsage(ServiceUsage serviceUsage) {

        Services service = servicesService.getServiceById(serviceUsage.getServiceId());

        serviceUsage.setStatus
                (false);
        serviceUsage.setTotal(service.getPrice() * serviceUsage.getAmount());
        return serviceUsageRepository.save(serviceUsage);
    }
    public void deleteServiceUsage(Long id) {
        serviceUsageRepository.deleteById(id);
    }

    public List<ServiceUsageDto> findByPartnerId(Long partnerId) {
        return serviceUsageRepository.findByPartnerId(partnerId);
    }

    public List<ServiceUsageDto> findByInvoiceId(Long serviceId) {
        return serviceUsageRepository.findByInvoiceId(serviceId);
    }
}
