package com.wzh.study.service.impl;

import com.wzh.study.entity.SysRole;
import com.wzh.study.mapper.SysRoleMapper;
import com.wzh.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole queryRoleByRoleId(String roleId) {

        SysRole sysRole = sysRoleMapper.queryRoleByRoleId(roleId);
        return sysRole;

    }
}
