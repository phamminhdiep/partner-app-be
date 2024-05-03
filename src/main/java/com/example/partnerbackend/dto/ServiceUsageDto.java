package com.example.partnerbackend.dto;

import java.util.Date;

public interface ServiceUsageDto {
    Long getId();
    Date getDate();
    Integer getAmount();

    String getName();
    String getDescription();
    Double getPrice();

    Double getTotal();
}
