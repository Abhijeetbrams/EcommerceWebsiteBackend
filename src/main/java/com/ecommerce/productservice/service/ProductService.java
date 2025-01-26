package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Page<Product> getProducts(int pageNumber, int pageSize);
    Product getProduct(Long productId);
    Product addProduct(Product product);
    void updateProduct(Long productId,Product product);
    void deleteProduct(Long productId);
    void replaceProduct(Long productId,Product product);
}
