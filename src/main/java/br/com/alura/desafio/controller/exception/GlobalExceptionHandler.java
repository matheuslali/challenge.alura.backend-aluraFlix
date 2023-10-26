package br.com.alura.desafio.controller.exception;

import br.com.alura.desafio.service.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ResponseEntity<String> HandleNoContentException(){
        return new ResponseEntity<>("Resource ID not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> HandleBusinessException(BusinessException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<String> handleUnexpectedException (Throwable unexpectedException){
        var message = "Unexpected server error";
        LOGGER.error(message, unexpectedException);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
