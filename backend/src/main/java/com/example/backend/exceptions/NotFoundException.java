package com.example.backend.exceptions;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {

    public NotFoundException(String s) {
        super(s);
    }
}
