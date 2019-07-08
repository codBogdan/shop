package org.fasttrackit.onlineshopapi.steps;


import org.fasttrackit.onlineshopapi.domain.Product.Product;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@Component
public class ProductSteps {

    @Autowired
    private ProductService productService;

    @Autowired
    public Product createProduct() {
            CreateProductRequest request = new CreateProductRequest();
            request.setName("Nivea");
            request.setPrice(99.99);
            request.setQuantity(10);

            Product createdProduct = productService.createProduct(request);

            assertThat(createdProduct, notNullValue());
            assertThat(createdProduct.getId(), greaterThan(0L));
            assertThat(createdProduct.getName(), is(request.getName()));
            assertThat(createdProduct.getPrice(), is(request.getPrice()));
            assertThat(createdProduct.getQuantity(), is(request.getQuantity()));

            return createdProduct;

    }
}
