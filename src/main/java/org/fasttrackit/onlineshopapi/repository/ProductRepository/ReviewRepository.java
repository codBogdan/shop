package org.fasttrackit.onlineshopapi.repository.ProductRepository;


import org.fasttrackit.onlineshopapi.domain.Product.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //queries by nested properties
    Page<Review> findByProductId(Long productId, Pageable pageable);
}
