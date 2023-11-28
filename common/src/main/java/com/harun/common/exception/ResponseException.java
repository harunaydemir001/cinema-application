package com.harun.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseException extends RuntimeException {

    String message;

    String code;

    HttpStatus httpStatus;

    String traceId;

    public ResponseException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public ResponseException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }


    public ResponseException(String code, String message, HttpStatus httpStatus, String traceId) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = code;
        this.traceId = traceId;
    }
}
