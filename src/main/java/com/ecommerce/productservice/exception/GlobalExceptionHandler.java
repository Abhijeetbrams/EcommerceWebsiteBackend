package com.ecommerce.productservice.exception;

import com.ecommerce.productservice.DTO.ResponseEntityDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormatException.class)
    public ResponseEntity<String> handleFormatException(FormatException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(OutOfRangeException.class)
    public ResponseEntity<String> handleOutOfRangeException(OutOfRangeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ResponseEntityDTO> handleDataValidationException(DataValidationException e) {
        ResponseEntityDTO response = new ResponseEntityDTO();
        response.setMessage(e.getMessage());
        response.setCode(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
