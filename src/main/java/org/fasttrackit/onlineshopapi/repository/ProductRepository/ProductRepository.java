package org.fasttrackit.onlineshopapi.repository.ProductRepository;

import org.fasttrackit.onlineshopapi.domain.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

    //queries derived from method name
    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product>findByNameContainingAndQuantityGreaterThanEqual(String partialName, int minQuantity, Pageable pageable);



}
