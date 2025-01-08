package com.ecommerce.productservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private Double price;
    private Category category;
    private String description;
    private String image;
}
