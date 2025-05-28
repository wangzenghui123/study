package com.wzh.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysUser;
import com.wzh.study.mapper.SysPermissionMapper;
import com.wzh.study.service.PermissionService;
import com.wzh.study.vo.reqVO.PermListReqVO;
import com.wzh.study.vo.respVO.PermListRespVO;
import com.wzh.study.vo.respVO.UserListRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public PermListRespVO getAllPerm(PermListReqVO permListReqVO) {

        PageHelper.startPage(permListReqVO.getPageNum(),permListReqVO.getPageSize());
        List<SysPermission> permList = sysPermissionMapper.getAllPerm();
        PageInfo<SysPermission> pageInfo = new PageInfo<>(permList);
        ArrayList<SysPermission> menuList = new ArrayList<>();

        PermListRespVO permListRespVO = new PermListRespVO();
        permListRespVO.setPermList(permList);
        permListRespVO.setCount(pageInfo.getTotal());
        return permListRespVO;
    }
}
