package com.wzh.study.exception;

import com.wzh.study.util.DataResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public DataResult error(BusinessException businessException){
        DataResult dataResult = new DataResult<>();
        dataResult.setCode(businessException.getCode());
        dataResult.setMsg(businessException.getMsg());
        return dataResult;
    }

}