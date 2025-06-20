package com.wzh.study.service.impl;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.entity.SysPermission;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.service.MenuService;
import com.wzh.study.service.PermissionService;
import com.wzh.study.service.RolePermissionService;
import com.wzh.study.service.UserRoleService;
import com.wzh.study.util.TokenUtil;
import com.wzh.study.vo.respVO.MenuResoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private UserRoleService userRoleService;


    @Autowired
    private RolePermissionService rolePermissionService;


    @Autowired
    private PermissionService permissionService;

    @Override
    public MenuResoVO getMenu() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new BusinessException(ResponseCode.NO_TOKEN);
        }
        String accessToken = (String) subject.getPrincipal();
        if (StringUtils.isEmpty(accessToken)) {
            throw new BusinessException(ResponseCode.NO_TOKEN);
        }
        String userId = TokenUtil.getUserId(accessToken);
        String roleId = userRoleService.getRoleId(userId);
        List<String> permissionIds = rolePermissionService.getPermissionIds(roleId);
        List<SysPermission> permissions = permissionService.getPermissions(permissionIds);
        MenuResoVO menuResoVO = new MenuResoVO();
        menuResoVO.setId(0);
        menuResoVO.setName("根目录");
        menuResoVO.isParent = true;
        List<MenuResoVO> menuResoVOList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getPid() == 0) {
                MenuResoVO child = new MenuResoVO();
                child.setPidName(permission.getPidName());
                child.setPid(permission.getPid());
                child.setType(permission.getType());
                child.setUrl(permission.getUrl());
                child.setName(permission.getName());
                child.setPerms(permission.getPerms());
                child.setOrderNum(permission.getOrderNum());
                child.setId(permission.getId());
                if(getChildren(permission.getId(),permissions).size() == 0){
                    child.isParent = false;
                }
                child.setChildren(getChildren(permission.getId(),permissions));
                menuResoVOList.add(child);

            }
        }
        Collections.sort(menuResoVOList, Comparator.comparingInt(MenuResoVO::getOrderNum));
        menuResoVO.setChildren((ArrayList<MenuResoVO>) menuResoVOList);
        return menuResoVO;
    }

    public ArrayList<MenuResoVO> getChildren(Integer id,List<SysPermission> permissions){
        ArrayList<MenuResoVO> menuResoVOArrayList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if(permission.getPid() == id){
                MenuResoVO child = new MenuResoVO();
                child.setPidName(permission.getPidName());
                child.setPid(permission.getPid());
                child.setType(permission.getType());
                child.setUrl(permission.getUrl());
                child.setName(permission.getName());
                child.setPerms(permission.getPerms());
                child.setOrderNum(permission.getOrderNum());
                child.setId(permission.getId());
                if(getChildren(permission.getId(),permissions).size() == 0){
                    child.isParent = false;
                }
                child.setChildren(getChildren(permission.getId(),permissions));
                menuResoVOArrayList.add(child);
            }
        }
        Collections.sort(menuResoVOArrayList, Comparator.comparingInt(MenuResoVO::getOrderNum));
        return menuResoVOArrayList;
    }
}


