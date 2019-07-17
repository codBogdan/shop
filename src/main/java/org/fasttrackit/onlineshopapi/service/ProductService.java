package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Product.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.ProductRepository.ProductRepository;
import org.fasttrackit.onlineshopapi.transfer.CreateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.GetProductsRequest;
import org.fasttrackit.onlineshopapi.transfer.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.product.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//IoC (inversion on control) and dependency injection
@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public Product createProduct(CreateProductRequest request){

        LOGGER.info("Creating product {}", request);

        Product product = objectMapper.convertValue(request, Product.class);
        //same result as above with objectMapper

//        Product product = new Product();
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        product.setQuantity(request.getQuantity());
//        product.setImage(request.getImage());

        return productRepository.save(product);
    }

    public Product getProduct(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving product {}", id);

        //Using Optional with orElseTrow
        return productRepository.findById(id)
                //Using Lambda expressions
                .orElseThrow(( ) ->  new ResourceNotFoundException("Product " + id + " does not exist"));
    }

    public Product updateProduct(long id, UpdateProductRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updatind product {} with {}", id, request);

        Product product = getProduct(id);


        BeanUtils.copyProperties(request, product);

        return productRepository.save(product);
    }


    public void deleteProduct(long id){
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }


    public Page<ProductDto> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products {}", request);

        Page<Product> products;


        if (request.getPartialName() != null && request.getMinimumQuantity() != null) {
           products = productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(), request.getMinimumQuantity(), pageable);

        } else if (request.getPartialName() != null) {
           products =  productRepository.findByNameContaining(request.getPartialName(), pageable);

        }else {
            products = productRepository.findAll(pageable);
        }


        List<ProductDto> productDtos = new ArrayList<>();

        products.getContent().forEach(product -> {ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());

        productDtos.add(productDto);
        });

        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }





}
