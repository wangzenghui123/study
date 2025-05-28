package com.wzh.study.mapper;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.vo.reqVO.PermListReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {

    List<SysPermission> getPermissions(List<String> permissionIds);

    List<SysPermission> getAllPerm();
}
