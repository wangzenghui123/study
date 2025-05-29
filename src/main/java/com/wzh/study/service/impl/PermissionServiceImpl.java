package com.wzh.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysUser;
import com.wzh.study.mapper.SysPermissionMapper;
import com.wzh.study.service.PermissionService;
import com.wzh.study.vo.reqVO.PermListReqVO;
import com.wzh.study.vo.respVO.MenuResoVO;
import com.wzh.study.vo.respVO.PermListRespVO;
import com.wzh.study.vo.respVO.UserListRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public PermListRespVO getAllPerm() {

        PermListRespVO permListRespVO = new PermListRespVO();
        List<SysPermission> allPerm = sysPermissionMapper.getAllPerm();
        ArrayList<SysPermission> menuList = new ArrayList<>();
        for (SysPermission sysPermission : allPerm) {
            if (sysPermission.getParentId() == 0) {
                List<SysPermission> children = getChildren(sysPermission.getId(), allPerm);
                SysPermission[] array = children.toArray(new SysPermission[children.size()]);
                sysPermission.setChildren(array);
                menuList.add(sysPermission);

            }
        }
        Collections.sort(menuList, Comparator.comparingInt(SysPermission::getOrderNum));
        permListRespVO.setData(menuList);
        System.out.println("查询的menu");
        System.out.println(permListRespVO);
        return permListRespVO;
    }

    //    private List<SysPermission> getChildren(Integer id,List<SysPermission> list){
//        ArrayList<SysPermission> child = new ArrayList<>();
//        for (SysPermission sysPermission : list) {
//            if(sysPermission.getParentId() == id){
//                child.add(sysPermission);
//            }
//        }
//        for (SysPermission sysPermission : child) {
//            List<SysPermission> children = getChildren(sysPermission.getId(), list);
//            SysPermission[] array =  children.toArray(new SysPermission[children.size()]);
//            sysPermission.setChildren(array);
//        }
//        return child;
//    }
    public ArrayList<SysPermission> getChildren(Integer id, List<SysPermission> permissions) {
        ArrayList<SysPermission> menuResoVOArrayList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getParentId() == id) {
                ArrayList<SysPermission> children = getChildren(permission.getId(), permissions);
                SysPermission[] array = children.toArray(new SysPermission[children.size()]);
                permission.setChildren(array);
                menuResoVOArrayList.add(permission);
            }
        }
        Collections.sort(menuResoVOArrayList, Comparator.comparingInt(SysPermission::getOrderNum));
        return menuResoVOArrayList;
    }
}
