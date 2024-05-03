package com.example.partnerbackend.module.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{
    List<Partner> findByNameContaining(String name);
    boolean existsByPhoneNumber(String phoneNumber);
}
