package com.akpp.baba_api_prototype;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;


@ControllerAdvice
public class BabaNotFoundExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BabaNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(BabaNotFoundException e, WebRequest request) {
        return this.handleExceptionInternal(
                e, Map.of("error", e.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request
        );
    }
}
