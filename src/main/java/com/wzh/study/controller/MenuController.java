package com.wzh.study.controller;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.service.menu.MenuService;
import com.wzh.study.util.DataResult;
import com.wzh.study.util.TokenUtil;
import com.wzh.study.vo.respVO.user.MenuResoVO;
import io.lettuce.core.output.ScanOutput;
import jakarta.annotation.Resource;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequiresPermissions("per")
    @GetMapping(name="/menu",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public DataResult<MenuResoVO> getMenu(){
        System.out.println(1111);
        DataResult<MenuResoVO> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        MenuResoVO menuResoVO = menuService.getMenu();
        dataResult.setData(menuResoVO);
        return dataResult;
    }
}
