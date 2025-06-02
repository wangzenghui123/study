package com.wzh.study.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

    private String id;

    private String username;

    private String salt;

    private String password;

    private String phone;

    private String deptId;

    private String realName;

    private String nickName;

    private String email;

    private Integer status;

    private Integer sex;

    private Integer deleted;

    private String createId;

    private String updateId;

    private Integer createWhere;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updateTime;

    private String deptName;
}
