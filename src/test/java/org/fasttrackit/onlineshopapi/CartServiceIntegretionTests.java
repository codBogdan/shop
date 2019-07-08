package org.fasttrackit.onlineshopapi;


import org.fasttrackit.onlineshopapi.domain.Product.Customer;
import org.fasttrackit.onlineshopapi.domain.Product.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.CartService;
import org.fasttrackit.onlineshopapi.steps.CustomerSteps;
import org.fasttrackit.onlineshopapi.steps.ProductSteps;
import org.fasttrackit.onlineshopapi.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshopapi.transfer.cart.CartDto;
import org.fasttrackit.onlineshopapi.transfer.product.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegretionTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerSteps customerSteps;

    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testAddToCart_whenValidRequest_thenCreateCart() throws ResourceNotFoundException {

        Customer customer = customerSteps.createCustomer();
        Product product = productSteps.createProduct();

        AddProductToCartRequest request  = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);


        CartDto cart = cartService.getCart(customer.getId());

        assertThat(cart, notNullValue());
        assertThat(cart.getCustomer(), notNullValue());
        assertThat(cart.getCustomer().getId(), is(customer.getId()));
        assertThat(cart.getCustomer().getLastname(), is(customer.getFirstname()));


        assertThat(cart.getProducts(), notNullValue());
        assertThat(cart.getProducts(), hasSize(1));

        ProductDto firstProduct = cart.getProducts().iterator().next();

        assertThat(firstProduct, notNullValue());
        assertThat(firstProduct.getName(), is(product.getName()));



    }

}
