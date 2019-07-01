package org.fasttrackit.onlineshopapi;


import org.fasttrackit.onlineshopapi.domain.Product.Customer;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.transfer.customer.CreateCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCustomer(){

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFirstname("Dorel");
        request.setLastname("Tarnacop");
        request.setEmail("dorelTarnacop@yahoo.com");


        Customer customer = customerService.createCustomer(request);

        assertThat(customer, notNullValue());
        assertThat(customer.getId(), greaterThan(0L));
        assertThat(customer.getFirstname(), is(request.getFirstname()));
        assertThat(customer.getLastname(), is(request.getLastname()));
        assertThat(customer.getEmail(), is(request.getEmail()));


    }
}
