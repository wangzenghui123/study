package com.wzh.study.vo.respVO.user;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespVO {

    private String accessToken;

    private String refreshToken;

    private String refreshAppToken;

}
