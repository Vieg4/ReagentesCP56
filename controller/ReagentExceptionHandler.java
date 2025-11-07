package com.laboratorio.inventario.controller;

import com.laboratorio.inventario.dto.ErrorResponse;
import com.laboratorio.inventario.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ReagentExceptionHandler {

    // Mapeia a ResourceNotFoundException (lançada por qualquer Service) para o Status HTTP 404 Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        
        ErrorResponse errorDetails = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(), 
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "") 
        );

        return new ResponseEntity<>(errorDetails, status);
    }
    
    // Outros tratamentos de exceção podem ser adicionados aqui (ex: handleMethodArgumentNotValid para 400 Bad Request)
}