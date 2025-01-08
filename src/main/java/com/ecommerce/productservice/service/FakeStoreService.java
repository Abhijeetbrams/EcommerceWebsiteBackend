package com.ecommerce.productservice.service;

import com.ecommerce.productservice.DTO.FakeStoreDTO;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreService implements ProductService{

    RestTemplate restTemplate;

    @Autowired
    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProducts() {
        return null;
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
