package com.wzh.study.shiro;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysRole;
import com.wzh.study.service.PermissionService;
import com.wzh.study.service.RolePermissionService;
import com.wzh.study.service.RoleService;
import com.wzh.study.service.UserRoleService;
import com.wzh.study.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String  accessToken =(String)principalCollection.getPrimaryPrincipal();
        Claims claims = TokenUtil.getClaims(accessToken);
        String  userId = (String)claims.get("userId");
        String roleId = userRoleService.getRoleId(userId);
        List<String> permissionIds = rolePermissionService.getPermissionIds(roleId);
        List<SysPermission> permissions = permissionService.getPermissions(permissionIds);

        SysRole sysRole = roleService.queryRoleByRoleId(roleId);
        HashSet<String> roleSet = new HashSet<>();
        roleSet.add(sysRole.getRoleName());

        HashSet<String> permissionSet = new HashSet<>();
        for (SysPermission permission : permissions) {
            permissionSet.add(permission.getPerms());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissionSet);
        info.setRoles(roleSet);
        System.out.println("权限。。。。。。。。。。。。。。。。。。");
        System.out.println(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomUsernamePasswordToken token = (CustomUsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),CustomRealm.class.getName());
        return simpleAuthenticationInfo;
    }
}
