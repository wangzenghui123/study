package com.wzh.study.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class RoleController {

    @GetMapping("/role")
    public String perm(){
        return "/role/role";
    }
}
