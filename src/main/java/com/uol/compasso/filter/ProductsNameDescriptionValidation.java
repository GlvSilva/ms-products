package com.uol.compasso.filter;

import com.uol.compasso.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class ProductsNameDescriptionValidation implements Specification<Products> {
    private final String q;

    @Override
    public Predicate toPredicate(Root<Products> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (q == null){
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        else {
            return criteriaBuilder.or(criteriaBuilder.like(root.<String>get("name"), "%" + this.q + "%"),
                    criteriaBuilder.like(root.<String>get("description"), "%" + this.q + "%"));
        }
    }
}
