package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
