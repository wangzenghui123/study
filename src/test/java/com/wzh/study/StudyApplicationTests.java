package com.wzh.study;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysUser;
import com.wzh.study.mapper.SysPermissionMapper;
import com.wzh.study.mapper.SysRolePermissionMapper;
import com.wzh.study.mapper.SysUserMapper;
import com.wzh.study.mapper.SysUserRoleMapper;
import com.wzh.study.util.PasswordUtil;
import com.wzh.study.util.TokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class StudyApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolepermissonMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

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

    @Test
    void contextLoads3() throws InterruptedException {
        List<String> permissionIds = sysRolepermissonMapper.getPermissionIds("1");
        for (SysPermission permission : sysPermissionMapper.getPermissions(permissionIds)) {
            System.out.println(permission.toString());
        }

    }


}
