package org.openlab.openlabcustomerservices.services;

import org.openlab.openlabcustomerservices.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservices.dto.CustomerResponseDTO;

import java.util.List;


public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustumer(String id);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> listCustomers();
}
