package com.ecommerce.productservice.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseEntityDTO {
    String message;
    HttpStatus code;
}
