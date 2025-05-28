package com.wzh.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzh.study.code.ResponseCode;
import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysRole;
import com.wzh.study.entity.SysUser;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.mapper.SysUserMapper;
import com.wzh.study.service.*;
import com.wzh.study.util.DataResult;
import com.wzh.study.util.PasswordUtil;
import com.wzh.study.util.TokenUtil;
import com.wzh.study.vo.reqVO.UserListReqVO;
import com.wzh.study.vo.reqVO.UserLoginReqVO;
import com.wzh.study.vo.respVO.UserListRespVO;
import com.wzh.study.vo.respVO.UserLoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;


    @Override
    public DataResult userLogin(UserLoginReqVO userLoginReqVO) {
        String username = userLoginReqVO.getUsername();
        String password = userLoginReqVO.getPassword();

        SysUser sysUser = sysUserMapper.queryUserByUsername(username);
        if(sysUser == null){
            throw new BusinessException(ResponseCode.NO_ACCOUNT);
        }
        String encryptPassword = PasswordUtil.encryptPassword(password, sysUser.getSalt());
        if(!sysUser.getPassword().equals(encryptPassword)){
            throw new BusinessException(ResponseCode.PASSWORD_ERROR);
        }
        if(sysUser.getStatus() != 1){
            throw new BusinessException(ResponseCode.ACCOUNT_LOCKED);
        }

        String roleId = userRoleService.getRoleId(sysUser.getId());
        SysRole sysRole = roleService.queryRoleByRoleId(roleId);
        List<String> permissionIds = rolePermissionService.getPermissionIds(roleId);
        List<SysPermission> permissions = permissionService.getPermissions(permissionIds);
        HashMap hashMap = new HashMap<>();
        HashSet permissionSet = new HashSet<>();
        for (SysPermission permission : permissions) {
            permissionSet.add(permission.getPerms());
        }
        HashSet roleSet = new HashSet();
        roleSet.add(sysRole.getRoleName());
        hashMap.put("permissionSet",permissionSet);
        hashMap.put("roleSet",roleSet);

        DataResult dataResult = new DataResult<>(ResponseCode.SUCCESS);
        UserLoginRespVO userLoginRespVO = new UserLoginRespVO();
        String accessToken = TokenUtil.createAccessToken(sysUser.getId(), sysUser.getUsername(), hashMap);
        String refreshToken = TokenUtil.createRefreshToken(sysUser.getId(), sysUser.getUsername(), hashMap);
        userLoginRespVO.setAccessToken(accessToken);
        userLoginRespVO.setRefreshToken(refreshToken);
        dataResult.setData(userLoginRespVO);
        return  dataResult;
    }

    @Override
    public String refreshAccessToken(String accessToken) {
        System.out.println("进入刷新token");
        String userId = TokenUtil.getUserId(accessToken);
        String username = TokenUtil.getUsername(accessToken);

        String accessToken1 = TokenUtil.createAccessToken(userId, username, new HashMap<>());
        return accessToken1;
    }

    @Override
    public UserListRespVO queryPageUserList(UserListReqVO userListReqVO) {

        PageHelper.startPage(userListReqVO.getPageNum(),userListReqVO.getPageSize());
        List<SysUser> userList = sysUserMapper.queryPageUserList(userListReqVO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);

        UserListRespVO userListRespVO = new UserListRespVO();
        userListRespVO.setUserList(userList);
        userListRespVO.setCount(pageInfo.getTotal());

        return userListRespVO;
    }
}
