package com.wzh.study.service;

import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.UserListReqVO;
import com.wzh.study.vo.reqVO.UserLoginReqVO;
import com.wzh.study.vo.respVO.UserListRespVO;

public interface UserService {

    DataResult userLogin(UserLoginReqVO userLoginReqVO);
    String refreshAccessToken(String accessToken);

    UserListRespVO queryPageUserList(UserListReqVO userListReqVO);

}
