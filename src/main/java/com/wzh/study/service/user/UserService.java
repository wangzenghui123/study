package com.wzh.study.service.user;

import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.user.UserLoginReqVO;

public interface UserService {

    DataResult userLogin(UserLoginReqVO userLoginReqVO);
}
