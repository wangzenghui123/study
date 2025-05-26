package com.wzh.study.service.impl;

import com.wzh.study.mapper.SysRolePermissionMapper;
import com.wzh.study.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<String> getPermissionIds(String roleId) {
        return sysRolePermissionMapper.getPermissionIds(roleId);
    }
}
