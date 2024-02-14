package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailDoesntExistError extends ResponseStatusException {
    public EmailDoesntExistError() {
        super(HttpStatus.CONFLICT, "Email doesn't exist");
    }
}
