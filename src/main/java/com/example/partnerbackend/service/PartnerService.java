package com.example.partnerbackend.service;

import com.example.partnerbackend.entity.Partner;
import com.example.partnerbackend.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {
    @Autowired
    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Page<Partner> getPartners(int page, int size) {
        Page<Partner> list =   partnerRepository.findAll(PageRequest.of(page - 1, size));
        return list;
    }

    public List<Partner> getPartners() {
        return partnerRepository.findAll();
    }

    public Partner getPartnerById(Long id) {
        return partnerRepository.findById(id).orElse(null);
    }

    public Partner savePartner(Partner partner) {
        if(partnerRepository.existsByPhoneNumber(partner.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number already exists");
        }
        return partnerRepository.save(partner);
    }

    public Partner savePartner(Long id, Partner partner) {
        Partner p = partnerRepository.findById(id).orElse(null);
        System.out.println(p.getPhoneNumber() + " " + partner.getPhoneNumber());
        if((!p.getPhoneNumber().equals(partner.getPhoneNumber()))){
            if( partnerRepository.existsByPhoneNumber(partner.getPhoneNumber())){
                throw new IllegalArgumentException("Phone number already exists");
            }
        }else {
            System.out.println("pass");
        }
        return partnerRepository.save(partner);
    }

    public void deletePartner(Long id) {
        partnerRepository.deleteById(id);
    }

    public List<Partner> findByNameContaining(String name) {
        return partnerRepository.findByNameContaining(name);
    }
}
