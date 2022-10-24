package org.openlab.openlabcustomerservices.mappers;

import org.mapstruct.Mapper;
import org.openlab.openlabcustomerservices.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservices.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservices.entities.Customer;

//Pour les applications basees sur Spring
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);

}


