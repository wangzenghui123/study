package com.wzh.study.service.impl;

import com.wzh.study.mapper.SysUserRoleMapper;
import com.wzh.study.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public String getRoleId(String userId) {
        return sysUserRoleMapper.getRoleId(userId);
    }
}
