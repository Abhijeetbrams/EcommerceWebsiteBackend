package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();
    Product getProduct(Long productId);
    Product addProduct(Product product);
    void updateProduct(Long productId,Product product);
    void deleteProduct(Long productId);
    void replaceProduct(Long productId,Product product);
}
