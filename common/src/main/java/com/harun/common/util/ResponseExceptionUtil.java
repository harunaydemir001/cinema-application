package com.harun.common.util;


import com.harun.common.exception.ResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseExceptionUtil {


    private final MessageUtil messageUtil;

    public void throwResponseException(HttpStatus httpStatus, String code) {
        throw new ResponseException(code, messageUtil.get(code), httpStatus);
    }

    public void throwResponseException(HttpStatus httpStatus, String code, Object... args) {
        throw new ResponseException(code, messageUtil.get(code, args), httpStatus);
    }

    public void throwResponseException(HttpStatus httpStatus, String subMessage, String code, Object... args) {
        throw new ResponseException(code, (messageUtil.get(code, args)) + " " + subMessage, httpStatus);
    }

    public void throwResponseException(String message, HttpStatus httpStatus) {
        throw new ResponseException(message, httpStatus);
    }
}
