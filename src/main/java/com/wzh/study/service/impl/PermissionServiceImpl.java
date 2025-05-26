package com.wzh.study.service.impl;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.mapper.SysPermissionMapper;
import com.wzh.study.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getPermissions(List<String> permissionIds) {
        return sysPermissionMapper.getPermissions(permissionIds);
    }
}
