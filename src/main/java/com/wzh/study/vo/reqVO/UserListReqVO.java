package com.wzh.study.vo.reqVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListReqVO {

    private Integer pageNum;

    private Integer pageSize;

    private String username;

    private String userId;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private String nickName;

}
