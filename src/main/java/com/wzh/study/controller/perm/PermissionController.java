package com.wzh.study.controller.perm;


import com.wzh.study.code.ResponseCode;
import com.wzh.study.service.PermissionService;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.PermListReqVO;
import com.wzh.study.vo.respVO.PermListRespVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permission")
    public String perm() {
        return "/perm/perm";
    }

    @PostMapping("/permission/list")
    @RequiresPermissions("sys:perm:query")
    @ResponseBody
    public DataResult<PermListRespVO> permList(@RequestBody PermListReqVO permListReqVO) {

        DataResult<PermListRespVO> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        dataResult.setData(permissionService.getAllPerm());
        return dataResult;
    }
}
