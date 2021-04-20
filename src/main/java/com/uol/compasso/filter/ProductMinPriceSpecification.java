package com.uol.compasso.filter;

import com.uol.compasso.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProductMinPriceSpecification implements Specification<Products> {
    private final BigDecimal min_price;

    @Override
    public Predicate toPredicate(Root<Products> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (min_price == null){
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        else {
            return criteriaBuilder.greaterThanOrEqualTo(root.<String>get("price"), this.min_price.toString());
        }
    }
}