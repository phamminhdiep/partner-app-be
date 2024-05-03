package com.example.partnerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@EnableJpaAuditing
public class PartnerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerBackendApplication.class, args);
	}

}
