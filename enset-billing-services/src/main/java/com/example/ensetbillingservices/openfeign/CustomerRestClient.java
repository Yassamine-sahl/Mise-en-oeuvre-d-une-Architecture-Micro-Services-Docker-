package com.example.ensetbillingservices.openfeign;

import com.example.ensetbillingservices.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    //Retourner un objet CUSTOMER
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@PathVariable(name = "id") String id);

    //Retourner la liste des CUSTOMERS
    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers();
}
