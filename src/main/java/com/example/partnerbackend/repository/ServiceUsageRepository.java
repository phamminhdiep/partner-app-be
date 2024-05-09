package com.example.partnerbackend.repository;

import com.example.partnerbackend.dto.ServiceUsageDto;
import com.example.partnerbackend.entity.ServiceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUsageRepository extends JpaRepository<ServiceUsage, Long> {
    @Query(value = "SELECT su.id , su.date, su.amount, su.total, s.name, s.description, s.price " +
            "FROM service_usage su " +
            "JOIN services s ON su.service_id = s.id " +
            "WHERE su.partner_id = :partnerId AND su.status = false",
            nativeQuery = true)
    List<ServiceUsageDto> findByPartnerId(@Param("partnerId") Long partnerId);

    @Query(value = "SELECT su.id , su.date, su.amount, su.total, s.name, s.description, s.price " +
            "FROM service_usage su " +
            "JOIN services s ON su.service_id = s.id " +
            "WHERE su.invoice_id = :invoiceId",
            nativeQuery = true)
    List<ServiceUsageDto> findByInvoiceId(Long invoiceId);

}
