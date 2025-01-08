package com.ecommerce.productservice.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FakeStoreDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
}
