package com.uol.compasso.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotBlank(message = "Value not Valid")
    private String name;
    @NotBlank(message = "Value not Valid")
    private String description;
    @NotNull
    @Min(value = 0)
    private Double price;

}
