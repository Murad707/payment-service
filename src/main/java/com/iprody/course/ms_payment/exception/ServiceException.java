package com.iprody.course.ms_payment.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException{

    private HttpStatus status;
    private String message;


    private static final String EMPTY_MESSAGE=" ";

    public ServiceException(Error error){
        this(error.getStatus(), error.getMessage());
        this.message = getMessage(EMPTY_MESSAGE);
        this.status = error.getStatus();
    }

    public ServiceException(Error error,int value){
        this(error.getStatus(),error.getMessage());
        this.message=getMessage(String.valueOf(value));
    }

    public ServiceException(Error error,String value){
        this(error.getStatus(),error.getMessage());
        this.message=getMessage(value);
    }

    public ServiceException(HttpStatus status,String message){
        super(message);
        this.status = status;
        this.message = message;
    }

    private String getMessage(String value){
        return String.format(
                this.message,value);}
}
