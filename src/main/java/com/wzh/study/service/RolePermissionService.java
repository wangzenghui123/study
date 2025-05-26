package com.wzh.study.service;

import java.util.List;

public interface RolePermissionService {

    List<String> getPermissionIds(String roleId);
}
