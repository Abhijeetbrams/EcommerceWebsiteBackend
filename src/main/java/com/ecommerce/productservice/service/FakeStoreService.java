package com.ecommerce.productservice.service;

import com.ecommerce.productservice.DTO.FakeStoreDTO;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreService")
public class FakeStoreService implements ProductService{

    RestTemplate restTemplate;
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate= redisTemplate;
    }

    @Override
    public Page<Product> getProducts(int pageNumber,int pageSize) {
        // Type Erasure - Since the Generics is compile time logic and we cannot define at runtime and it makes no sense,
        // Thus List.class or List<FakeStoreDTO> will not work correctly.
        // We need to store the response in Array
        // List<FakeStoreDTO> fakeStoreDTOs = restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
//        FakeStoreDTO[] fakeStoreDTOs = restTemplate.getForObject("http://localhost:8080/products", FakeStoreDTO[].class);
//        List<Product> products = new ArrayList<>();
//        for (FakeStoreDTO fakeStoreDTO : fakeStoreDTOs) {
//            products.add(convertFakeStoreDTOToProduct(fakeStoreDTO));
//        }
//
//        return products;
        return null;
    }

    public Product getProduct(Long productId) {
        // Here we are fetching the product from the Redis Cache and since we're using same
        // Redis Cluster thus we're mentioning PRODUCTS as the MAP name and product_product_id as the key
        // to fetch the product from the Redis Cache.
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","product_"+productId);


        System.out.println("Fetching the product from the API");
        if(product != null) {
            return product;
        }

        // If the product is not found in the Redis Cache then we will fetch it from the external API
        // For Load Balanced RestTemplate we need to use the service name instead of the URL, and Service name will be the
        // name of the service mentioned in the Service Registry.
        // Here we're using the RestTemplate to fetch the data from the external API
        FakeStoreDTO fakeStoreDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+ productId, FakeStoreDTO.class);

        // Convert FakeStoreDTO to Product
        Product productFromApi = convertFakeStoreDTOToProduct(fakeStoreDTO);

        // Here PRODUCTS is the MAP name and product_product_id is the key
        // We're storing the product in the Redis Cache

        redisTemplate.opsForHash().put("PRODUCTS","product_"+productId,productFromApi);
        
        return productFromApi;
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
