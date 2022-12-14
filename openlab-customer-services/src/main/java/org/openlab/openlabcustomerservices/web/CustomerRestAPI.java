package org.openlab.openlabcustomerservices.web;

import org.openlab.openlabcustomerservices.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservices.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservices.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CustomerRestAPI {
    private CustomerService customerService ;

    public CustomerRestAPI(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<CustomerResponseDTO> allCustomers(){

        return customerService.listCustomers();
    }
    @PostMapping(path="/customers")
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        customerRequestDTO.setId(UUID.randomUUID().toString());
        return customerService.save(customerRequestDTO);
    }

    @GetMapping(path = "/customers/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable String id){
        return customerService.getCustumer(id);
    }
}
