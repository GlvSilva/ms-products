package com.uol.compasso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "tb_products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", length = 50, nullable = false)
    @NotBlank
    private String name;
    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    @NotBlank
    private String description;
    @Column(name = "PRICE", nullable = false)
    private Double price;
}
