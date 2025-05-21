package com.wzh.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {

    private String id;

    private String userId;

    private String roleId;
}
