package com.wzh.study.util;

import com.wzh.study.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DataResult <T>{

    private int code;

    private String msg;

    private T data;

    public DataResult(){}

    public DataResult(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public DataResult(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public DataResult(ResponseCode responseCode,T data){
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

}
