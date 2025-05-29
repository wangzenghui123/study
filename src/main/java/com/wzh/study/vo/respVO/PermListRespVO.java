package com.wzh.study.vo.respVO;

import com.wzh.study.entity.SysPermission;
import com.wzh.study.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermListRespVO {

    private Integer code = 0;

    private Long count = 1000L;

    private List<SysPermission> data;
}
