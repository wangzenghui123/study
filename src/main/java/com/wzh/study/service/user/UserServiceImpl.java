package com.wzh.study.service.user;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.entity.SysUser;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.mapper.SysUserMapper;
import com.wzh.study.util.DataResult;
import com.wzh.study.util.PasswordUtil;
import com.wzh.study.util.TokenUtil;
import com.wzh.study.vo.reqVO.user.UserLoginReqVO;
import com.wzh.study.vo.respVO.user.UserLoginRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public DataResult userLogin(UserLoginReqVO userLoginReqVO) {
        String username = userLoginReqVO.getUsername();
        String password = userLoginReqVO.getPassword();

        SysUser sysUser = sysUserMapper.queryUserByUsername(username);
        if(sysUser == null){
            throw new BusinessException(ResponseCode.NO_ACCOUNT);
        }
        String encryptPassword = PasswordUtil.encryptPassword(password, sysUser.getSalt());
        if(!sysUser.getPassword().equals(encryptPassword)){
            throw new BusinessException(ResponseCode.PASSWORD_ERROR);
        }
        if(sysUser.getStatus() != 1){
            throw new BusinessException(ResponseCode.ACCOUNT_LOCKED);
        }

        DataResult dataResult = new DataResult<>(ResponseCode.SUCCESS);
        UserLoginRespVO userLoginRespVO = new UserLoginRespVO();
        String accessToken = TokenUtil.createAccessToken(sysUser.getId(), sysUser.getUsername(), new HashMap<>());
        String refreshToken = TokenUtil.createRefreshToken(sysUser.getId(), sysUser.getUsername(), new HashMap<>());
        userLoginRespVO.setAccessToken(accessToken);
        userLoginRespVO.setRefreshToken(refreshToken);
        dataResult.setData(userLoginRespVO);
        return  dataResult;
    }
}
