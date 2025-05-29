package com.wzh.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission implements Serializable {

    private Integer id;

    private Integer code;

    private String name;

    private String perms;

    private String url;

    private String method;

    private Integer parentId;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String pidName;

    private SysPermission[] children;
}
