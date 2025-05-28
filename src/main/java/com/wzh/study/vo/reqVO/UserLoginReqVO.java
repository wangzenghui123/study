package com.wzh.study.vo.reqVO;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginReqVO {

    private String username;

    private String password;
}
