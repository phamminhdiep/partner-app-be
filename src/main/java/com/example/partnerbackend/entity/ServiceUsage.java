package com.example.partnerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ServiceUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Services service;
    @ManyToOne
    private Partner partner;
    @ManyToOne
    private Schedule schedule;
    @ManyToOne
    private Invoice invoice;
    @CreatedDate
    private Date date;
    private Integer amount;
    private Double total;
    private boolean status;
}
