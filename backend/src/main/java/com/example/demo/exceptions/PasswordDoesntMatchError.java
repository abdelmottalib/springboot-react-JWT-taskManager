package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordDoesntMatchError extends ResponseStatusException {
    public PasswordDoesntMatchError() {
        super(HttpStatus.CONFLICT, "Password doesn't match");
    }
}
