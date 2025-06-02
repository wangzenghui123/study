package com.wzh.study.mapper;

import com.wzh.study.entity.SysUser;
import com.wzh.study.vo.reqVO.UserListReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser queryUserByUsername(String username);

    List<SysUser> queryPageUserList(UserListReqVO userListReqVO);

    Integer updateUser(SysUser sysUser);
}


