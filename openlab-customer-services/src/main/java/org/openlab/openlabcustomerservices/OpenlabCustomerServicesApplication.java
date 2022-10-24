package org.openlab.openlabcustomerservices;


import org.openlab.openlabcustomerservices.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservices.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlabCustomerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenlabCustomerServicesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("C01","Adria","adria@gmail.com"));
            customerService.save(new CustomerRequestDTO("C02","OpenLab","openLab@gmail.com"));
        };
    }
}












