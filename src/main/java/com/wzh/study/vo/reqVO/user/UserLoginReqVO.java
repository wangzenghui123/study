package com.wzh.study.vo.reqVO.user;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginReqVO {

    private String username;

    private String password;
}
