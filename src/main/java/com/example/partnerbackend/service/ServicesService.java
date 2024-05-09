package com.example.partnerbackend.service;

import com.example.partnerbackend.entity.Services;
import com.example.partnerbackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicesService {


    private final ServiceRepository serviceRepository;
    @Autowired
    public ServicesService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
    public Page<Services> getServices(int page, int size) {
        return serviceRepository.findAll(PageRequest.of(page - 1, size));
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }
    public Services getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }
    public Services saveService(Services service) {
        return serviceRepository.save(service);
    }
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
