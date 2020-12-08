package com.ocr.library.web.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class OuvrageIntrouvableException extends RuntimeException {

    public OuvrageIntrouvableException(String s) {

        super(s);
    }

}
