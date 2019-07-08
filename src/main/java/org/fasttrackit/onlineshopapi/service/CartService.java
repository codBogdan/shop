package org.fasttrackit.onlineshopapi.service;


import org.fasttrackit.onlineshopapi.domain.Product.Cart;
import org.fasttrackit.onlineshopapi.domain.Product.Customer;
import org.fasttrackit.onlineshopapi.domain.Product.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.ProductRepository.CartRepository;
import org.fasttrackit.onlineshopapi.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshopapi.transfer.cart.CartDto;
import org.fasttrackit.onlineshopapi.transfer.customer.CustomerDto;
import org.fasttrackit.onlineshopapi.transfer.product.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }


    @Transactional
    public void addProductToCart(AddProductToCartRequest request) throws ResourceNotFoundException {
        LOGGER.info("Saving to cart {}", request);

        Customer customer = customerService.getCustomer(request.getCustomerId());

        Cart cart = new Cart();
        cart.setCustomer(customer);

        Product product = productService.getProduct(request.getProductId());
        cart.addProduct(product);


        cartRepository.save(cart);


    }

    @Transactional
    public CartDto getCart(Long customerId) throws ResourceNotFoundException{
        Cart cart = cartRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException( "Cart" + customerId + "do not exist"));

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(cart.getCustomer().getId());
        customerDto.setFirstname(cart.getCustomer().getFirstname());
        customerDto.setLastname(cart.getCustomer().getLastname());

        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setCustomer(customerDto);

        cart.getProducts().forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());

            cartDto.getProducts().add(productDto);
        });


        return cartDto;
    }

}
