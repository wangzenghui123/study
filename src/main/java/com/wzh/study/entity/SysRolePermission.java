package com.wzh.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission {

    private String id;

    private String roleId;

    private String permissionId;
}
