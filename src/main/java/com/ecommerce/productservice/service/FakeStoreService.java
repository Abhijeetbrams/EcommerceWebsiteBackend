package com.ecommerce.productservice.service;

import com.ecommerce.productservice.DTO.FakeStoreDTO;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreService")
public class FakeStoreService implements ProductService{

    RestTemplate restTemplate;

    @Autowired
    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProducts() {
        // Type Erasure - Since the Generics is compile time logic and we cannot define at runtime and it makes no sense,
        // Thus List.class or List<FakeStoreDTO> will not work correctly.
        // We need to store the response in Array
        // List<FakeStoreDTO> fakeStoreDTOs = restTemplate.getForObject("https://fakestoreapi.com/products", List.class);

        FakeStoreDTO[] fakeStoreDTOs = restTemplate.getForObject("http://localhost:8080/products", FakeStoreDTO[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreDTO fakeStoreDTO : fakeStoreDTOs) {
            products.add(convertFakeStoreDTOToProduct(fakeStoreDTO));
        }

        return products;
    }

    public Product getProduct(Long productId) {
        // This will help to make the API calls to the external parties
        FakeStoreDTO fakeStoreDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+ productId, FakeStoreDTO.class);

        // Convert FakeStoreDTO to Product
         return convertFakeStoreDTOToProduct(fakeStoreDTO);
    }

    public Product addProduct(Product product) {
        return null;
    }

    public void updateProduct(Long productId,Product product) {}
    public void deleteProduct(Long productId) {}
    public void replaceProduct(Long productId,Product product) {}

    public Product convertFakeStoreDTOToProduct(FakeStoreDTO fakeStoreDTO) {
        Product product = new Product();
        product.setTitle(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setDescription(fakeStoreDTO.getDescription());
        product.setImage(fakeStoreDTO.getImage());
        product.setId(fakeStoreDTO.getId());

        Category category = new Category();
        category.setValue(fakeStoreDTO.getCategory());
        product.setCategory(category);
        return product;
    }

}
