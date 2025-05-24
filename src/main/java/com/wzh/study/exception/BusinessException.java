package com.wzh.study.exception;

import com.wzh.study.code.ResponseCode;
import lombok.Data;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;

@Data
public class BusinessException extends ShiroException {

    private int code;

    private String msg;

    public BusinessException(ResponseCode responseCode){

        this.code = responseCode.getCode();

        this.msg = responseCode.getMsg();

    }
}
