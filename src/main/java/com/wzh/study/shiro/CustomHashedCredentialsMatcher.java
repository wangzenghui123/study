package com.wzh.study.shiro;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.redis.RedisService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken accessToken = (CustomUsernamePasswordToken) token;
        String accessToken1 = accessToken.getAccessToken();
        if (RedisService.hasKey("access_token_" + accessToken1)) {
            return true;
        }else{
            throw new BusinessException(ResponseCode.ACCESS_TOKEN_EXPIRED);
        }

    }
}
