package com.example.demo.exeption;

public class WrongStateException extends RuntimeException {
    public WrongStateException(String text) {
        super(text);
    }
}
