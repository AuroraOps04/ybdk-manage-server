package com.study.ybdk.advice;

import com.study.ybdk.annotation.IgnoreResponseAdvice;
import com.study.ybdk.vo.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return filter(methodParameter);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if( o == null ){
            return BaseResponse.SUCCESS();
        }
        if( o instanceof BaseResponse){
            return o;
        }
        return BaseResponse.SUCCESS(o);
    }

    private boolean filter(MethodParameter methodParameter) {
        String classFullName = methodParameter.getDeclaringClass().getName();
        if (!StringUtils.contains(classFullName, "com.study.ybdk.controller")) {
            return false;
        }
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return !methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class);
    }
}
