package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TitleAlreadyExistsException extends RuntimeException{
    public TitleAlreadyExistsException(String message) {
        super(message);
    }
}
