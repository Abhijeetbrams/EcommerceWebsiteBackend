package com.ecommerce.productservice.service;

import com.ecommerce.productservice.DTO.FakeStoreDTO;
import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ProductDBService")
public class ProductDBService implements ProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductDBService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
      return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        Optional<Product>  productOptional= productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product Not Found with this Id: "+productId);
        }
        return productOptional.get();
    }

    public Product addProduct(Product product) {
        return null;
    }

    public void updateProduct(Long productId,Product product) {}
    public void deleteProduct(Long productId) {}
    public void replaceProduct(Long productId,Product product) {}


}
