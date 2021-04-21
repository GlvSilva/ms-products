package com.uol.compasso.service;

import com.uol.compasso.entity.Products;
import com.uol.compasso.exception.ProductNotFoundException;
import com.uol.compasso.filter.ProductMaxPriceSpecification;
import com.uol.compasso.filter.ProductMinPriceSpecification;
import com.uol.compasso.filter.ProductsNameDescriptionValidation;
import com.uol.compasso.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Products> getProducts() {

        return productsRepository.findAll();
    }

    public Products getProductsById(Long id) {
        return Optional.of(productsRepository.findById(id))
                .map(p -> p.orElseThrow(ProductNotFoundException::new)).get();
    }

    public Products createProduct(Products products) {
        return Optional.of(productsRepository.save(products))
                .orElseThrow(ProductNotFoundException::new);
    }

    public Products updateProduct(Long id, Products products) {
        return productsRepository.findById(id).map(obj -> {
            obj.setName(products.getName());
            obj.setDescription(products.getDescription());
            obj.setPrice(products.getPrice());
            return productsRepository.save(obj);
        }).orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity deleteProduct(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productsRepository.delete(products);
        return new ResponseEntity(HttpStatus.OK);
    }

    public List<Products> search(String q, BigDecimal min_price, BigDecimal max_price) {
        Specification<Products> specification = Specification
                .where(new ProductsNameDescriptionValidation(q))
                .and(new ProductMinPriceSpecification(min_price))
                .and(new ProductMaxPriceSpecification(max_price));
        return productsRepository.findAll(specification);
    }
}
