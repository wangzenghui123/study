package com.wzh.study.mapper;

import com.wzh.study.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {

    List<String> getPermissionIds(String roleId);
}
