package com.wzh.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/index/404")
    public String index404(){
        System.out.println("api/index/404");
        return "/404";
    }


}
