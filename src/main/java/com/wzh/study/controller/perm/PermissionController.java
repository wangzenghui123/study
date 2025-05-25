package com.wzh.study.controller.perm;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.util.DataResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class PermissionController {

    @GetMapping("/permission")
    public String perm(){
        return "/perm/perm";
    }

    @GetMapping("/permission/test")
    @RequiresPermissions("per")
    @ResponseBody
    public DataResult permTest(){

        DataResult dataResult = new DataResult<>(ResponseCode.SUCCESS);

        return dataResult;
    }
}
