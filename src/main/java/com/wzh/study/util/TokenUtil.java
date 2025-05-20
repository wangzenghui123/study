package com.wzh.study.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class TokenUtil implements InitializingBean {

    @Value("${jwt.signKey}")
    private String signKey;

    @Value("${jwt.accessTokenDurationTime}")
    private String accessTokenDurationTime;

    @Value("${jwt.refreshTokenDurationTime}")
    private String refreshTokenDurationTime;

    @Value("${jwt.refreshTokenAppDurationTime}")
    private String refreshTokenAppDurationTime;


    private static String SIGN_KEY;

    private static String ACCESS_TOKEN_DURATION_TIME;

    private static String REFRESH_TOKEN_DURATION_TIME;

    private static String REFRESH_TOKEN_APP_DURATION_TIME;

    public static String createAccessToken(String userId,String username,Map<String,Object> claims){
        String accessToken = createToken(userId, username, ACCESS_TOKEN_DURATION_TIME, claims);
        return accessToken;
    }
    public static String createRefreshToken(String userId,String username,Map<String,Object> claims){
        String refreshToken = createToken(userId, username, REFRESH_TOKEN_DURATION_TIME, claims);
        return refreshToken;
    }
    public static String createRefreshAppToken(String userId,String username,Map<String,Object> claims){
        String refreshAppToken = createToken(userId, username, REFRESH_TOKEN_APP_DURATION_TIME, claims);
        return refreshAppToken;
    }
    public static String createToken(String userId,String username,String tokenDurationTime,Map<String,Object> claims){
        Duration duration = Duration.parse(tokenDurationTime);
        claims.put("userId",userId);
        claims.put("username",username);
        claims.put("expireTime",System.currentTimeMillis()+duration.toMillis());
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY.getBytes())
                .compact();
        return jwt;
    }

   public static boolean isTokenExpired(String token){
       Claims claims = Jwts.parser()
               .setSigningKey(SIGN_KEY.getBytes())
               .parseClaimsJws(token)
               .getBody();
       return System.currentTimeMillis() > (long) claims.get("expireTime");
   }


    @Override
    public void afterPropertiesSet() throws Exception {
        SIGN_KEY = signKey;
        ACCESS_TOKEN_DURATION_TIME = accessTokenDurationTime;
        REFRESH_TOKEN_DURATION_TIME = refreshTokenDurationTime;
        REFRESH_TOKEN_APP_DURATION_TIME = refreshTokenAppDurationTime;
    }
}
