package com.besidetech.aims.controller;

import com.besidetech.aims.dto.ApiException;
import com.besidetech.aims.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(ApiException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getCode(), ex.getMessage()));
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("000", ex.getMessage()));
    }
}
