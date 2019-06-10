package org.fasttrackit.onlineshopapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Product.Customer;
import org.fasttrackit.onlineshopapi.repository.ProductRepository.CustomerRepository;
import org.fasttrackit.onlineshopapi.transfer.customer.CreateCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper ;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public Customer createCustomer(CreateCustomerRequest request){
        Customer customer = objectMapper.convertValue(request, Customer.class);
        return  customerRepository.save(customer);
    }


    public void deleteCustomer(long id){
        LOGGER.info("Deleting product {}", id);
        customerRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }

}
