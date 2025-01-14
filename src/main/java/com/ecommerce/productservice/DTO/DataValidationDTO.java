package com.ecommerce.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataValidationDTO {
    String fieldName;
    String message;

    public DataValidationDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
