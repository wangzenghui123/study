package com.wzh.study.shiro;

import com.alibaba.fastjson2.JSON;
import com.wzh.study.code.ResponseCode;
import com.wzh.study.exception.BusinessException;
import com.wzh.study.util.DataResult;
import com.wzh.study.util.TokenUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomVerifyTokenFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token="";
//        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
//             token = httpServletRequest.getHeader("AccessToken");
//            if (StringUtils.isEmpty(token)) {
//                token = httpServletRequest.getParameter("AccessToken");
//            }
//        }
        System.out.println("进入exceptionFilter");
        String accessToken = httpServletRequest.getHeader("AccessToken");
        String refreshToken = httpServletRequest.getHeader("RefreshToken");
        if(TokenUtil.isTokenExpired(refreshToken)){
            resonseJson(servletResponse,ResponseCode.REFRESH_TOKEN_EXPIRED);
            return false;
        }
        if(TokenUtil.isTokenExpired(accessToken)){
            System.out.println("已过期");
            resonseJson(servletResponse,ResponseCode.ACCESS_TOKEN_EXPIRED);
            return false;
        }
        return true;
    }

    public void resonseJson(ServletResponse servletResponse,ResponseCode responseCode) throws IOException {
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        DataResult dataResult = new DataResult<>(responseCode);
        String jsonString = JSON.toJSONString(dataResult);
        System.out.println(jsonString);
        writer.print(jsonString);
        writer.close();
    }


}
