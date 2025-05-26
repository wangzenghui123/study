package com.wzh.study.service;

import com.wzh.study.entity.SysUser;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.user.UserListReqVO;
import com.wzh.study.vo.reqVO.user.UserLoginReqVO;
import com.wzh.study.vo.respVO.user.UserListRespVO;

import java.util.List;

public interface UserService {

    DataResult userLogin(UserLoginReqVO userLoginReqVO);
    String refreshAccessToken(String accessToken);

    UserListRespVO queryPageUserList(UserListReqVO userListReqVO);

}
