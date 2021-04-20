package com.uol.compasso.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super("Produto não foi encontrado na base");
    }}

