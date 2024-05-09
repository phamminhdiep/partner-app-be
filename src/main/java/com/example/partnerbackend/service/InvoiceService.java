package com.example.partnerbackend.service;

import com.example.partnerbackend.entity.Invoice;
import com.example.partnerbackend.entity.ServiceUsage;
import com.example.partnerbackend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final PartnerService partnerService;

    private final ServiceUsageService serviceUsageService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, PartnerService partnerService, ServiceUsageService serviceUsageService) {
        this.invoiceRepository = invoiceRepository;
        this.partnerService = partnerService;
        this.serviceUsageService = serviceUsageService;
    }

    public Page<Invoice> getInvoices(int page, int size) {
        return invoiceRepository.findAll(PageRequest.of(page - 1 , size));
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(Invoice invoice) {
        invoice.setPartnerId(partnerService.getPartnerById(invoice.getPartnerId()).getId());
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Transactional
    public Invoice generateInvoice(Long partnerId, List<Long> serviceUsageIds) {
        Invoice savedInvoice = invoiceRepository.save(new Invoice());

        savedInvoice.setPartnerId(partnerId);
        double total = 0;
        for(Long serviceUsageId : serviceUsageIds) {
            ServiceUsage serviceUsage = serviceUsageService.getServiceUsageById(serviceUsageId);
            serviceUsage.setStatus(true);
            serviceUsage.setInvoiceId(savedInvoice.getId());
            total += serviceUsageService.getServiceUsageById(serviceUsageId).getTotal();
        }
        savedInvoice.setTotal(total);
        return savedInvoice;
    }
}
