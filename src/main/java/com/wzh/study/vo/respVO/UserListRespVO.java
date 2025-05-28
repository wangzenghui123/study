package com.wzh.study.vo.respVO;

import com.wzh.study.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListRespVO {

    private Long count;

    private List<SysUser> userList;
}
