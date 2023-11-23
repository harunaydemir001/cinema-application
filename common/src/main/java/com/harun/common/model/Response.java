package com.harun.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private Object result;
    private String traceId;
    @JsonRawValue
    private String causedBy;

    public Response() {
    }

    public Response(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Response(Object result) {
        this.result = result;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(Object result, String message) {
        this.result = result;
        this.message = message;
    }
}