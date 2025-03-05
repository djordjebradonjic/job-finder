package com.example.demo.controller;


import exceptions.BadRequestException;
import exceptions.JobNotFoundException2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex){

        Map<String, Object> errorResponse= new HashMap<>();
        errorResponse.put("status" , HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad Request");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JobNotFoundException2.class)
    public ResponseEntity<Map<String,Object>> handleJobNotFound(JobNotFoundException2 ex){
        Map<String, Object> errorResponse= new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND);
        errorResponse.put("error","Job not found with ID:" + ex.getJobId());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
