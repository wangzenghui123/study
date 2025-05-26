package com.wzh.study.controller;

import com.wzh.study.code.ResponseCode;
import com.wzh.study.service.MenuService;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.respVO.user.MenuResoVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/menu")
    @ResponseBody
    public DataResult<MenuResoVO> getMenu(){
        DataResult<MenuResoVO> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        MenuResoVO menuResoVO = menuService.getMenu();
        dataResult.setData(menuResoVO);
        return dataResult;
    }
}
