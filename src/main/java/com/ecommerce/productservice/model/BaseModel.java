package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass // Since we don't want to create a entity of BaseModel in the database, only properties of base model
// will be inherited into the Child class like Product and Category.
// which means in the table of child class will contains attributes from Parent class as well as Child class.
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // for incremental Id's
    private Long id;

    @Column(nullable = false, updatable = false) //
    private Long createdAt;

    @Column(nullable = false)
    private Long lastUpdatedAt;

    @PrePersist // When a new entity is created, createdAt and lastUpdatedAt are set to the current timestamp.
    protected void onCreate() {
        long currentTime = Instant.now().toEpochMilli();
        this.createdAt = currentTime;
        this.lastUpdatedAt = currentTime;
    }

    @PreUpdate // On updating an existing entity, only lastUpdatedAt is updated.
    protected void onUpdate() {
        this.lastUpdatedAt = Instant.now().toEpochMilli();
    }
}
