package com.wzh.study;

import com.wzh.study.entity.SysUser;
import com.wzh.study.mapper.SysUserMapper;
import com.wzh.study.util.PasswordUtil;
import com.wzh.study.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class StudyApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void contextLoads() throws InterruptedException {
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("name","wzh");
        String s = TokenUtil.createAccessToken("","",claims);

        for (;;){
            System.out.println(TokenUtil.isTokenExpired(s));
            Thread.sleep(1000);
        }

    }
    @Test
    void contextLoads1() throws InterruptedException {
        String salt = PasswordUtil.generateSalt();
        String password= PasswordUtil.encryptPassword("123456", salt);
        System.out.println(salt);
        System.out.println(password);
    }
    @Test
    void contextLoads2() throws InterruptedException {
        SysUser wzh1 = sysUserMapper.queryUserByUsername("wzh1");
        System.out.println(wzh1);
    }

}
