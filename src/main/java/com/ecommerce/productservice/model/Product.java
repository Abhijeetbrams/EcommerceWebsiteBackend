package com.ecommerce.productservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="products")
public class Product extends BaseModel{
    private String title;
    private Double price;
    @ManyToOne
    @JsonIgnoreProperties("products") // Ignore the `products` field in the `Category` during serialization otherwise infinite recursion will occur.
    private Category category;
    private String description;
    private String image;
}
