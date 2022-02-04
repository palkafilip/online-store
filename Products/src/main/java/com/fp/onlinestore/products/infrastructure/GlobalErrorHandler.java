package com.fp.onlinestore.products.infrastructure;

import com.fp.onlinestore.products.infrastructure.exceptions.CustomerNotFoundException;
import com.fp.onlinestore.products.infrastructure.exceptions.ProductNotFoundException;
import com.fp.onlinestore.products.infrastructure.exceptions.RequiredQuantityNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ProductNotFoundException.class })
    protected ResponseEntity<Object> handle404Exceptions(final RuntimeException ex, final WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { CustomerNotFoundException.class, RequiredQuantityNotAvailableException.class })
    protected ResponseEntity<Object> handle500Exceptions(final RuntimeException ex, final WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
