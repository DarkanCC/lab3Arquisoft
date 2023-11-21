package com.udea.lab3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidRating extends RuntimeException {
    public InvalidRating(String message) {
        super(message);
    }
}
