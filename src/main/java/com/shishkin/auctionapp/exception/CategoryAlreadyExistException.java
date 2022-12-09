package com.shishkin.auctionapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryAlreadyExistException extends ResponseStatusException {
    public CategoryAlreadyExistException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
