package com.wzh.study.mapper;

import com.wzh.study.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

    String getRoleId(String userId);
}
