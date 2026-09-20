package com.lfonta.theowlery.exceptions;

import com.lfonta.theowlery.dto.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            ApiException exception
    ) {
        ErrorResponse response = new ErrorResponse(exception.getName(), exception.getMessage());

        Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        logger.error(exception.getCause().toString());

        return ResponseEntity
                .status(exception.getStatus())
                .body(response);
    }
}
