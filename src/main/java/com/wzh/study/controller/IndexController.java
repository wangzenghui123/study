package com.wzh.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "/user/index";
    }

}
