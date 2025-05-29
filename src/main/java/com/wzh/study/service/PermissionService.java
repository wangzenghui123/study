package com.wzh.study.service;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.vo.reqVO.PermListReqVO;
import com.wzh.study.vo.respVO.PermListRespVO;

import java.util.List;

public interface PermissionService {

    List<SysPermission> getPermissions(List<String> permissionIds);

    PermListRespVO getAllPerm();
}
