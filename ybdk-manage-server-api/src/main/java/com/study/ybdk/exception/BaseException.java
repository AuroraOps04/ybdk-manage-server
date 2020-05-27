package com.study.ybdk.exception;

import com.study.ybdk.vo.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private Integer code;
    public BaseException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
    public BaseException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
}
