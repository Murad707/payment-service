package com.iprody.course.ms_payment.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionMessageModel {
    private String message;


    @Override
    public String toString() {
        return "{\"message\":\"" + message + "\"}";
    }
}
