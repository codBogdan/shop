package org.fasttrackit.onlineshopapi.repository.ProductRepository;

import org.fasttrackit.onlineshopapi.domain.Product.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository <Cart, Long> {

}
