package com.ecommerce.productservice.controller;


import com.ecommerce.productservice.exception.OutOfRangeException;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    ProductService productService;

    @Autowired
    public ProductController(@Qualifier("ProductDBService")ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws Exception {
        Product product=null;
        try{
            product = productService.getProduct(productId);
        }catch(NullPointerException e){
            throw new OutOfRangeException("Please enter in the range of product id between 1-20, Id typed: "+productId);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {}

    @PatchMapping("/{id}")
    public void patchProduct(@PathVariable("id") Long productId, @RequestBody Product product) {}

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {}
}
