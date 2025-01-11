package com.ecommerce.productservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass // Since we don't want to create a entity of BaseModel in the database, only properties of base model
// will be inherited into the Child class like Product and Category.
// which means in the table of child class will contains attributes from Parent class as well as Child class.
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for incremental Id's
    private Long id;
    private Long createdAt;
    private Long lastUpdatedAt;
}
