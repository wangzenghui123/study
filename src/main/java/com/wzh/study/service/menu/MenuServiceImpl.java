package com.wzh.study.service.menu;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.entity.SysPermission;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.mapper.SysPermissionMapper;
import com.wzh.study.mapper.SysRolePermissionMapper;
import com.wzh.study.mapper.SysUserRoleMapper;
import com.wzh.study.util.TokenUtil;
import com.wzh.study.vo.respVO.user.MenuResoVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {


    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;


    @Autowired
    private SysRolePermissionMapper sysRolepermissonMapper;


    @Autowired
    private SysPermissionMapper sysPermissionMapper;

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
        String roleId = sysUserRoleMapper.getRoleId(userId);
        List<String> permissionIds = sysRolepermissonMapper.getPermissionIds(roleId);
        List<SysPermission> permissions = sysPermissionMapper.getPermissions(permissionIds);
        MenuResoVO menuResoVO = new MenuResoVO();
        menuResoVO.setId("0");
        menuResoVO.setName("根目录");
        List<MenuResoVO> menuResoVOList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if (permission.getPid().equals("0")) {
                MenuResoVO child = new MenuResoVO();
                child.setPidName(permission.getPidName());
                child.setPid(permission.getPid());
                child.setType(permission.getType());
                child.setUrl(permission.getUrl());
                child.setName(permission.getName());
                child.setPerms(permission.getPerms());
                child.setOrderNum(permission.getOrderNum());
                child.setId(permission.getId());
                child.setChildren(getChildren(permission.getId(),permissions));

                menuResoVOList.add(child);

            }
        }
        System.out.println(menuResoVO.toString());
        return menuResoVO;
    }

    public ArrayList<MenuResoVO> getChildren(String id,List<SysPermission> permissions){
        ArrayList<MenuResoVO> menuResoVOArrayList = new ArrayList<>();
        for (SysPermission permission : permissions) {
            if(permission.getPid().equals(id)){
                MenuResoVO child = new MenuResoVO();
                child.setPidName(permission.getPidName());
                child.setPid(permission.getPid());
                child.setType(permission.getType());
                child.setUrl(permission.getUrl());
                child.setName(permission.getName());
                child.setPerms(permission.getPerms());
                child.setOrderNum(permission.getOrderNum());
                child.setId(permission.getId());
                child.setChildren(getChildren(permission.getId(),permissions));
                menuResoVOArrayList.add(child);
            }
        }
        return menuResoVOArrayList;
    }
}


