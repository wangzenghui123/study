package com.wzh.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission implements Serializable {

    private String id;

    private String roleId;

    private String permissionId;
}
