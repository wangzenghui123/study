package com.wzh.study.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String accessToken;

    private String refreshToken;


    public CustomUsernamePasswordToken(String accessToken,String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public Object getPrincipal() {
        return accessToken;
    }
}
