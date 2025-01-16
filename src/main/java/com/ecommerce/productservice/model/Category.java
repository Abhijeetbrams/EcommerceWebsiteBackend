package com.ecommerce.productservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="categories")
public class Category extends BaseModel{
    private String value;

    @OneToMany(mappedBy = "category") // this basically said that the category field in the Product class is the owner of the relationship
            // and don't create a new column in the category table to store the relationship
    List<Product> products; // This is not mandatory, but it is a good practice to have a list of products in the category
    // if you want to fetch all the products for a particular category
}
