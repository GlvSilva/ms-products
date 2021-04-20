package com.uol.compasso.exception.advice;

import com.uol.compasso.response.ErrorObjectReturn;
import com.uol.compasso.exception.BadRequestException;
import com.uol.compasso.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

@RestControllerAdvice
public class ProductNotFoundExceptionAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> notFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(ErrorObjectReturn
                .builder()
                .status_code("404")
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public   ResponseEntity<Object> handleMethodNotAllowedExceptionException(MethodNotAllowedException ex) {
        return ResponseEntity.status(405).body(ErrorObjectReturn
                .builder()
                .status_code("405")
                .message("Metodo não é permitido")
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public   ResponseEntity<Object> badRequest(BadRequestException ex) {
        return ResponseEntity.status(400).body(ErrorObjectReturn
                .builder()
                .status_code("400")
                .message("Requisição inválida!")
                .build());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public   ResponseEntity<Object> missingRequestHeaderException(MissingRequestHeaderException ex) {
        return ResponseEntity.status(400).body(ErrorObjectReturn
                .builder()
                .status_code("401")
                .message("Erro na requisição, um cabeçalho é necessário.")
                .build());
    }
}