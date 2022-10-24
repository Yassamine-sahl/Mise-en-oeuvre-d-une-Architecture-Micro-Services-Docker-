package com.example.ensetbillingservices;

import com.example.ensetbillingservices.dto.InvoiceRequestDTO;
import com.example.ensetbillingservices.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@EnableFeignClients
@SpringBootApplication
public class EnsetBillingServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetBillingServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(54300),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(12000),"C02"));

        };
    }

}
