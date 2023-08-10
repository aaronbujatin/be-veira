package com.aaronbujatin.beveira.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {

    private String name;
    private BigDecimal price;
    private String brand;
    private String color;
    private String imageUrl;
    private String catalog;
    private String size;
    private int stock;

}