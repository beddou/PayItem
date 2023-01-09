package com.pay.payitem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NoEntityDeletedException extends RuntimeException{

    public NoEntityDeletedException(String s){
        super(s);
    }
}

