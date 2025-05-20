package com.wzh.study.mapper;

import com.wzh.study.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    SysUser queryUserByUsername(String username);
}


