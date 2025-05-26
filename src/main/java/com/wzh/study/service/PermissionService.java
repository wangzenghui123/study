package com.wzh.study.service;

import com.wzh.study.entity.SysPermission;

import java.util.List;

public interface PermissionService {

    List<SysPermission> getPermissions(List<String> permissionIds);
}
