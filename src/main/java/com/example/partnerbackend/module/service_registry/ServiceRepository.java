package com.example.partnerbackend.module.service_registry;

import com.example.partnerbackend.module.service_registry.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
}
