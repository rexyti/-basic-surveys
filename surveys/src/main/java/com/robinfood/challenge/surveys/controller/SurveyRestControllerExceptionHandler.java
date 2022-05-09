package com.robinfood.challenge.surveys.controller;

import com.robinfood.challenge.surveys.application.dto.error.SurveyError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SurveyRestControllerExceptionHandler {
    @ExceptionHandler(value = {
            javax.validation.ConstraintViolationException.class,
            org.springframework.dao.DataIntegrityViolationException.class
    })
    public ResponseEntity<SurveyError> handleAppMessageProcessorException(Exception ex) {
        return new ResponseEntity<>(new SurveyError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

