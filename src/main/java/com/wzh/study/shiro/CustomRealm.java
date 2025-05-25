package com.wzh.study.shiro;

import com.wzh.study.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.collections.ArrayStack;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.HashSet;

public class CustomRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String  accessToken =(String)principalCollection.getPrimaryPrincipal();
        Claims claims = TokenUtil.getClaims(accessToken);
        String  userId = (String)claims.get("userId");
        System.out.println("设置权限");
        HashSet<String> set = new HashSet<>();
        set.add("per");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),CustomRealm.class.getName());
        return simpleAuthenticationInfo;
    }
}
