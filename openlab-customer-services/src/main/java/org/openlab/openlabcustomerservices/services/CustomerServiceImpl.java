package org.openlab.openlabcustomerservices.services;

import org.openlab.openlabcustomerservices.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservices.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservices.entities.Customer;
import org.openlab.openlabcustomerservices.mappers.CustomerMapper;
import org.openlab.openlabcustomerservices.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository ;
    private CustomerMapper customerMapper ;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO) ;
        Customer savedCustomer = customerRepository.save(customer) ;
        return customerMapper.customerToCustomerResponseDTO(savedCustomer) ;

    }

    @Override
    public CustomerResponseDTO getCustumer(String id) {
        Customer customer = customerRepository.getById(id);
        return  customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO) ;
        Customer updatedCustomer = customerRepository.save(customer) ;
        return customerMapper.customerToCustomerResponseDTO(updatedCustomer) ;

    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers =customerRepository.findAll() ;
        List<CustomerResponseDTO> customerResponseDTOS =customers.stream()
                .map(customer -> customerMapper.customerToCustomerResponseDTO(customer))
                .collect(Collectors.toList());
        return  customerResponseDTOS;
    }
}

