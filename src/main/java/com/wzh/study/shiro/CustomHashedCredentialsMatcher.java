package com.wzh.study.shiro;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.redis.RedisService;
import com.wzh.study.util.TokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        System.out.println("进入匹配器");
//        CustomUsernamePasswordToken accessToken = (CustomUsernamePasswordToken) token;
//        String accessToken1 = accessToken.getAccessToken();
//        String refreshToken = accessToken.getRefreshToken();
//
//        if(TokenUtil.isTokenExpired(refreshToken)){
//            throw new BusinessException(ResponseCode.REFRESH_TOKEN_EXPIRED);
//        }
//        if(TokenUtil.isTokenExpired(accessToken1)){
//            System.out.println("已过期");
//            BusinessException businessException = new BusinessException(ResponseCode.ACCESS_TOKEN_EXPIRED);
//            throw businessException;
//        }



        return true;

    }
}
