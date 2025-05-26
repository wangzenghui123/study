package com.wzh.study.mapper;

import com.wzh.study.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {

    SysRole queryRoleByRoleId(String roleId);

}
