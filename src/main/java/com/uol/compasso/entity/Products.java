package com.uol.compasso.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
@ToString
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", length = 50, nullable = false)
    @NotBlank(message = "O campo name deve ser preenchido")
    private String name;
    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    @NotBlank(message = "O campo description deve ser preenchido")
    private String description;
    @Column(name = "PRICE", nullable = false)
    @Min(value = 0)
    private Double price;
}
