package com.wzh.study.code;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;


public enum ResponseCode {

    SUCCESS(4000000,"响应成功"),


    //token相关
    ACCESS_TOKEN_EXPIRED(4000001,"令牌过期"),

    REFRESH_TOKEN_EXPIRED(4000002,"令牌失效，请重新登录"),

    NO_AUTHORITY(4000003,"您没有权限，请联系管理员"),

    NO_TOKEN(4000004,"未携带令牌，请重新登录"),



    //账户相关
    NO_ACCOUNT(5000000,"账户不存在"),

    PASSWORD_ERROR(5000001,"密码错误"),

    ACCOUNT_LOCKED(5000002,"账户锁定，请联系管理员");






    private int code;


    private String msg;

    ResponseCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
