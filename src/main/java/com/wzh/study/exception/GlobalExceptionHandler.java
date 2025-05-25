package com.wzh.study.exception;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.util.DataResult;
import org.apache.shiro.authz.AuthorizationException;
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

    @ExceptionHandler(AuthorizationException.class)
    public DataResult unauthorizedException(AuthorizationException e){
        DataResult dataResult = new DataResult<>(ResponseCode.NO_AUTHORITY);
        return dataResult;
    }

}