package com.study.ybdk.vo;

import lombok.Data;


public enum ResultCode {
    SUCCESS(200, "success"),
    SYSTEM_ERROR(500, "system error"),
    NOT_FOUND(404, "page not found"),
    METHOD_NOTFUOUND(405, "not support request method "),
    NOTSUPPORTED_MEDIATYPE(415, "not support media type"),
    FAIL(1001, "error"),
    PARAMS_ERROR(1002, "invalid param"),
    PARAMS_ISNULL(1003, "params is required");

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
