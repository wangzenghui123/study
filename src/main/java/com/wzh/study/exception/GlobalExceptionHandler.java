package com.wzh.study.exception;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.util.DataResult;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public DataResult error(BusinessException businessException){
        DataResult dataResult = new DataResult<>();
        dataResult.setCode(businessException.getCode());
        dataResult.setMsg(businessException.getMsg());
        return dataResult;
    }

    @ExceptionHandler(AuthenticationException.class)
    public DataResult error1(AuthenticationException authenticationException){
        DataResult dataResult = new DataResult<>(ResponseCode.ACCESS_TOKEN_EXPIRED);
        return dataResult;
    }

}