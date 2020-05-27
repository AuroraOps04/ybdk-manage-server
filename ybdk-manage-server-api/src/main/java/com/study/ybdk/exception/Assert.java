package com.study.ybdk.exception;

import com.study.ybdk.vo.ResultCode;

public class Assert {
    public static void isNotNull(Object o, String paramsName){
        if(o == null){
            throw new BaseException(ResultCode.PARAMS_ISNULL.getCode(), "param " + paramsName + " is required");
        }
    }
    public static void paramInvalid(String paramName){
        throw new BaseException(ResultCode.PARAMS_ERROR.getCode(), "param " + paramName + " Invalid");
    }
    public static void businessError(){
        throw new BaseException(ResultCode.FAIL);
    }
    public static void businessError(String message){
        throw new BaseException(ResultCode.FAIL.getCode(), message);
    }
}
