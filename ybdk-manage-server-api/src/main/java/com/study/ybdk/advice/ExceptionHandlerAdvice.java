package com.study.ybdk.advice;

import com.study.ybdk.exception.BaseException;
import com.study.ybdk.vo.BaseResponse;
import com.study.ybdk.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {


    @ExceptionHandler(BaseException.class)
    public BaseResponse businessExceptionHandler(BaseException e) {
        log.error(e.getMessage(), e);
        return new BaseResponse(e.getCode(), e.getMessage());
    }


    /**
     * 处理 405 异常
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return Response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse HttpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException e
    ) {
        log.error("not support request method", e);
        return new BaseResponse(ResultCode.METHOD_NOTFUOUND);
    }

    /**
     * 处理 415 异常
     *
     * @param e HttpMediaTypeNotSupportedException
     * @return Response
     */

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public BaseResponse HttpMediaTypeNotSupportedExceptionHandler(
            HttpMediaTypeNotSupportedException e
    ) {
        log.error("not support media type", e);
        return new BaseResponse(ResultCode.NOTSUPPORTED_MEDIATYPE);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.SERVER_ERROR();
    }
}
