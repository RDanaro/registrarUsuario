package com.example.user.exception;

import com.example.user.dto.Error;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManager {
    private static final Logger logger = LogManager.getLogger(ExceptionManager.class);

    @Value("${register.user.error}")
    private String msgRegisterError;

    public ExceptionManager() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ResponseEntity primerError (Exception error){
        Error fail = new Error(msgRegisterError);
        logger.info(error.getMessage());
        return new ResponseEntity<>(fail, HttpStatus.BAD_REQUEST);
    }
}
