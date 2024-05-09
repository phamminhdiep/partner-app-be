package com.example.partnerbackend.repository;

import com.example.partnerbackend.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{
    List<Partner> findByNameContaining(String name);
    boolean existsByPhoneNumber(String phoneNumber);
}
