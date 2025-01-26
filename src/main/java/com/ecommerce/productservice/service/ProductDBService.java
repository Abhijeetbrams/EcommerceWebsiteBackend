package com.ecommerce.productservice.service;

import com.ecommerce.productservice.DTO.DataValidationDTO;
import com.ecommerce.productservice.DTO.FakeStoreDTO;
import com.ecommerce.productservice.exception.DataValidationException;
import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Sort;


import javax.xml.crypto.Data;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service("ProductDBService")
public class ProductDBService implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ArrayList<DataValidationDTO> stringDataValidationDTOS = new ArrayList<>();
    ArrayList<DataValidationDTO> numberDataValidationDTOS = new ArrayList<>();
    ArrayList<DataValidationDTO> objectDataValidationDTOS = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Logic to execute after the bean is created
        System.out.println("MyService has been initialized!");
        // Creating ArrayList for data validation.
        DataValidationDTO titleCheck = new DataValidationDTO("title","Product Title cannot be blank");
        DataValidationDTO priceCheck = new DataValidationDTO("price","Product Price cannot be blank");
        DataValidationDTO categoryCheck = new DataValidationDTO("category","Product Category cannot be Null");
        DataValidationDTO imageCheck = new DataValidationDTO("image","Product Image cannot be blank");

        stringDataValidationDTOS.add(titleCheck);
        numberDataValidationDTOS.add(priceCheck);
        objectDataValidationDTOS.add(categoryCheck);
        stringDataValidationDTOS.add(imageCheck);
    }

    @Autowired
    public ProductDBService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by("price").ascending()));
    }

    public Product getProduct(Long productId) {
        Optional<Product>  productOptional= productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product Not Found with this Id: "+productId);
        }
        return productOptional.get();
    }

    public Product addProduct(Product product) {
        // Data validation Logic

        // String Data Validation
        for(DataValidationDTO dataValidationDTO : stringDataValidationDTOS) {
            String fieldName = dataValidationDTO.getFieldName();

            // Dynamic Getter Invocation and callGetter Method.
            String value=(String)callGetter(product, fieldName);

            // Checking Null or Blank using StringUtils
            if(StringUtils.isBlank(value)){
                throw new DataValidationException(dataValidationDTO.getMessage());
            }
        }

        // Object Data Validation
        for(DataValidationDTO dataValidationDTO : objectDataValidationDTOS) {
            String fieldName = dataValidationDTO.getFieldName();

            // Dynamic Getter Invocation and callGetter Method.
            Category value=(Category) callGetter(product, fieldName);

            if(value==null){
                throw new DataValidationException(dataValidationDTO.getMessage());
            }else {
                if(value.getId()!=null){
                    if(value.getValue()==null){
                        throw new DataValidationException("Category Value cannot be Null");
                    }else{
                        Optional<Category> category=categoryRepository.findById(value.getId());
                        if(category.isPresent()){
                            if(category.get().getValue() != value.getValue()){
                                throw new DataValidationException("Category Value is not Equal to Category DB : "+ value.getValue());
                            }else{
                                categoryRepository.save(value);
                            }
                        }else{
                            categoryRepository.save(value);
                        }
                    }
                }else{
                    categoryRepository.save(value);
                }
            }
        }

        // Number Data Validation
        for(DataValidationDTO dataValidationDTO : numberDataValidationDTOS) {
            String fieldName = dataValidationDTO.getFieldName();

            // Dynamic Getter Invocation and callGetter Method.
            Object value=callGetter(product, fieldName);

            // Checking Null or Blank using StringUtils
            if(value==null){
                throw new DataValidationException(dataValidationDTO.getMessage());
            }
        }


        return productRepository.save(product);
    }

    public void updateProduct(Long productId,Product product) {}
    public void deleteProduct(Long productId) {}
    public void replaceProduct(Long productId,Product product) {}


    // Misc Function
    public static Object callGetter(Object object, String fieldName) {
        try {
            // Construct the getter method name
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            // Get the method object from the class
            Method method = object.getClass().getMethod(methodName);

            // Invoke the getter method
            return method.invoke(object);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("No getter found for field: " + fieldName, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call getter for field: " + fieldName, e);
        }
    }

}
