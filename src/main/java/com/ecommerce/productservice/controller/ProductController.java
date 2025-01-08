package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product= productService.getProduct(productId);
        return product;
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {}

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {}

    @PatchMapping("/{id}")
    public void patchProduct(@PathVariable("id") Long productId, @RequestBody Product product) {}

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {}
}
