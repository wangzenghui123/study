package com.wzh.study.controller.user;

import com.wzh.study.service.user.UserService;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.user.UserLoginReqVO;
import com.wzh.study.vo.respVO.user.UserLoginRespVO;
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
        return "user/login";
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
}
