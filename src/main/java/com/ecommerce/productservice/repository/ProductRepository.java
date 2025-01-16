package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.model.TitleAndPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    // Alias is mandatory in the query and the alias name should match the field name in the projection interface
    // The query should return the fields in the same order as in the projection interface
    @Query("Select p.title as title,p.price as price from products p where p.title = :title")
    public TitleAndPriceProjection findTitleAndPriceByTitle(@Param("title") String title);
}
