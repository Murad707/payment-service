package com.iprody.course.ms_payment.controller.exception;

import com.iprody.course.ms_payment.dto.ResponseDto;
import com.iprody.course.ms_payment.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessageModel> handleException(Exception e) {
        ExceptionMessageModel errorMessage = new ExceptionMessageModel(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionMessageModel> handleException(ServiceException e) {
        ExceptionMessageModel errorMessage = new ExceptionMessageModel(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(errorMessage,e.getStatus());
    }
}
