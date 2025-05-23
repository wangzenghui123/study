package com.wzh.study.controller.perm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PermissionController {

    @GetMapping("/permission")
    public String perm(){
        return "/perm/perm";
    }
}
