package com.uol.compasso.service;

import com.uol.compasso.entity.Product;
import com.uol.compasso.exception.ProductNotFoundException;
import com.uol.compasso.filter.ProductMaxPriceSpecification;
import com.uol.compasso.filter.ProductMinPriceSpecification;
import com.uol.compasso.filter.ProductNameDescriptionValidation;
import com.uol.compasso.repository.ProductRepository;
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
public class ProductService {

    @Autowired
    ProductRepository productsRepository;

    public List<Product> getProducts() {

        return productsRepository.findAll();
    }

    public Product getProductsById(Long id) {
        return Optional.of(productsRepository.findById(id))
                .map(p -> p.orElseThrow(ProductNotFoundException::new)).get();
    }

    public Product createProduct(Product products) {
        return Optional.of(productsRepository.save(products))
                .orElseThrow(ProductNotFoundException::new);
    }

    public Product updateProduct(Long id, Product products) {
        return productsRepository.findById(id).map(obj -> {
            obj.setName(products.getName());
            obj.setDescription(products.getDescription());
            obj.setPrice(products.getPrice());
            return productsRepository.save(obj);
        }).orElseThrow(ProductNotFoundException::new);
    }

    public ResponseEntity deleteProduct(Long id) {
        Product products = productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productsRepository.delete(products);
        return new ResponseEntity(HttpStatus.OK);
    }

    public List<Product> search(String q, BigDecimal min_price, BigDecimal max_price) {
        Specification<Product> specification = Specification
                .where(new ProductNameDescriptionValidation(q))
                .and(new ProductMinPriceSpecification(min_price))
                .and(new ProductMaxPriceSpecification(max_price));
        return productsRepository.findAll(specification);
    }
}
