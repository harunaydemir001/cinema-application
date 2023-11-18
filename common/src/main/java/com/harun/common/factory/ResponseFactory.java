package com.harun.common.factory;

import com.harun.common.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseFactory {

    private ResponseFactory() {
    }

    private static final String SUCCESS_MESSAGE = "Success";

    public static ResponseEntity<Response> createResponse(Object result, HttpStatus status) {
        return new ResponseEntity<>(new Response(result), status);
    }

    public static ResponseEntity<Response> createResponse(String code, String message, HttpStatus status) {
        return new ResponseEntity<>(new Response(code, message), status);
    }

    public static ResponseEntity<Response> createResponse(String code, String message, HttpStatus status, String traceId) {
        return new ResponseEntity<>(new Response(code, message, traceId), status);
    }

    public static ResponseEntity<Response> createSuccessResponse() {
        return new ResponseEntity<>(new Response(SUCCESS_MESSAGE), HttpStatus.OK);
    }

    public static ResponseEntity<Response> createResponse(HttpStatus httpStatus) {
        return new ResponseEntity<>(new Response(SUCCESS_MESSAGE), httpStatus);
    }
}
