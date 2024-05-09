package com.example.partnerbackend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    @NotBlank(message = "Code is mandatory")
    private String code;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @Column(name = "phone_number", unique = true)
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

}
