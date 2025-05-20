package com.wzh.study.exception;

import com.wzh.study.code.ResponseCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException{

    private int code;

    private String msg;

    public BusinessException(ResponseCode responseCode){

        this.code = responseCode.getCode();

        this.msg = responseCode.getMsg();

    }
}
