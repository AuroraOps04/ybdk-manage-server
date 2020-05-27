package com.study.ybdk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    public static BaseResponse SUCCESS() {
        return new BaseResponse(ResultCode.SUCCESS);
    }

    public static BaseResponse SUCCESS(Object data) {
        return new BaseResponse(ResultCode.SUCCESS, data);
    }

    public static BaseResponse FAIL(String message) {
        BaseResponse res = FAIL();
        res.setMessage(message);
        return res;
    }

    public static BaseResponse FAIL() {
        return new BaseResponse(ResultCode.FAIL);
    }

    public static BaseResponse SERVER_ERROR() {
        return new BaseResponse(ResultCode.SYSTEM_ERROR);
    }

    public BaseResponse(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = null;

    }

    public BaseResponse(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
    private Object data;
}
