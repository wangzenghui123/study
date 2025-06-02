package com.wzh.study.controller.user;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.entity.SysUser;
import com.wzh.study.service.UserService;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.UserListReqVO;
import com.wzh.study.vo.reqVO.UserLoginReqVO;
import com.wzh.study.vo.respVO.UserLoginRespVO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }

    @GetMapping("/user")
    public String user(){
        return "/user/user";
    }

    @PostMapping(value = "/user/userLogin",produces ={MediaType.APPLICATION_JSON_VALUE} )
    @ResponseBody
    public DataResult<UserLoginRespVO> userLogin(@RequestBody UserLoginReqVO userLoginReqVO){

        return userService.userLogin(userLoginReqVO);

    }

    @GetMapping("/user/token")
    @ResponseBody
    public DataResult refreshAccessToken(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken1 = httpServletRequest.getHeader("AccessToken");
        System.out.println(accessToken1);
        String accessToken = userService.refreshAccessToken(accessToken1);

        DataResult<String> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        dataResult.setData(accessToken);
        return dataResult;

    }

    @PostMapping(value = "/user/list",produces ={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @RequiresPermissions("sys:user:query")
    public DataResult<Object> userList(@RequestBody UserListReqVO userListReqVO) {

        DataResult<Object> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        dataResult.setData(userService.queryPageUserList(userListReqVO));
        return dataResult;

    }
    @PostMapping(value = "/user/updateUser",produces ={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @RequiresPermissions("sys:user:edit")
    public DataResult updateUser(@RequestBody SysUser sysUser){
        ResponseCode responseCode = userService.updateUser(sysUser);
        DataResult dataResult = new DataResult(responseCode);
        return dataResult;
    }

}
