package com.example.demo.exceptions;

public class BadRequestException extends  RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
