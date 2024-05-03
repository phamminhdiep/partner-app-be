package com.example.partnerbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentConfig {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("API APPLICATION")
                        .description("API for application")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Phạm Minh Điệp")
                                .email("phamminhdiep24042002@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Profile developer")
                        .url("https://www.facebook.com/dieppham.2404/"));
    }
}
