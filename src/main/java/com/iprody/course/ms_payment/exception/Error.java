package com.iprody.course.ms_payment.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum Error {

    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Payment with id=%s Not Found"),
    PAYMENT_IS_NEGATIVE(HttpStatus.BAD_REQUEST, "Payment is Negative"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request body=%s");

    private final HttpStatus status;
    private final String message;
}
