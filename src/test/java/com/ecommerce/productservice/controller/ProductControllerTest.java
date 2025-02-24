package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest // To make all the dependencies available
class ProductControllerTest {
//
//    @Autowired
//    ProductController productController;
//
//    @MockitoBean// To mock the service
//    ProductService productService; // Inject the mocked service
//
//
//    @Test
//    void getProduct() throws Exception {
//
//        // Arrange
//        Product expectedProduct=new Product();
//        expectedProduct.setId(1L);
//        expectedProduct.setTitle("Product 1");
//        expectedProduct.setPrice(100.0);
//        expectedProduct.setImage("image1.jpg");
//        expectedProduct.setCategory(null);
//
//        // Mocking the behavior of the ProductService

//        when(productService.getProduct(1L)).thenReturn(expectedProduct);
//
//        // Act
//        Product actualProduct = productController.getProduct(1L).getBody();
//
//        // Assert
//        assertEquals(expectedProduct, actualProduct);
//    }
//
//    @Test
//    void getProducts() {
//        // Arrange
//
//        List<Product> expectedProducts = new ArrayList<>();
//
//        //
//        Product product1=new Product();
//        product1.setId(1L);
//        product1.setTitle("Product 1");
//        product1.setPrice(100.0);
//        product1.setImage("image1.jpg");
//        product1.setCategory(null);
//
//        Product product2=new Product();
//        product2.setId(2L);
//        product2.setTitle("Product 2");
//        product2.setPrice(200.0);
//        product2.setImage("image2.jpg");
//        product2.setCategory(null);
//
//        expectedProducts.add(product1);
//        expectedProducts.add(product2);
//
//        // Mocking the behavior of the ProductService
//        when(productService.getProducts()).thenReturn(expectedProducts);
//
//        // Act
//        List<Product> actualProducts = productController.getProducts();
//
//        // Assert
//        assertEquals(expectedProducts, actualProducts);
//    }
//
//    @Test
//    void addProduct() {
//    }
//
//    @Test
//    void updateProduct() {
//    }
//
//    @Test
//    void patchProduct() {
//    }
//
//    @Test
//    void deleteProduct() {
//    }
}