package org.fasttrackit.onlineshopapi.transfer.cart;


import org.fasttrackit.onlineshopapi.transfer.customer.CustomerDto;
import org.fasttrackit.onlineshopapi.transfer.product.ProductDto;

import java.util.HashSet;
import java.util.Set;

public class CartDto {

    private Long id;
    private CustomerDto customer;
    private Set<ProductDto> products = new HashSet<>();

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}
