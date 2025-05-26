package com.wzh.study.util;

import com.wzh.study.service.PermissionService;
import com.wzh.study.service.RolePermissionService;
import com.wzh.study.service.UserRoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@Component
@Data
public class TokenUtil implements InitializingBean {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

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

    public static String getAccessTokenDurationTime(){
        return ACCESS_TOKEN_DURATION_TIME;
    }
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
       Claims claims = getClaims(token);
       return System.currentTimeMillis() > (long) claims.get("expireTime");
   }

    public static Boolean validateToken(String token) {
        Claims claimsFromToken = getClaims(token);
        return (null!=claimsFromToken && !isTokenExpired(token));
    }

   public static Claims getClaims(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims;
   }
   public static String getUserId(String token){
       Claims claims = getClaims(token);
       String userId = (String)claims.get("userId");
       return userId;
   }
    public static String getUsername(String token){
        Claims claims = getClaims(token);
        String username = (String)claims.get("username");
        return username;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SIGN_KEY = signKey;
        ACCESS_TOKEN_DURATION_TIME = accessTokenDurationTime;
        REFRESH_TOKEN_DURATION_TIME = refreshTokenDurationTime;
        REFRESH_TOKEN_APP_DURATION_TIME = refreshTokenAppDurationTime;
    }
}
