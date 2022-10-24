package org.openlab.openlabcustomerservices.repositories;

import org.openlab.openlabcustomerservices.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer getById(String id);
}
