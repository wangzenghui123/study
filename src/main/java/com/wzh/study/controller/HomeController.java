package com.wzh.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
