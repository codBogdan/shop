package org.fasttrackit.onlineshopapi.repository.ProductRepository;

import org.fasttrackit.onlineshopapi.domain.Product.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAll();
}
