package com.wzh.study.shiro;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPObject;
import com.wzh.study.code.ResponseCode;
import com.wzh.study.util.DataResult;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.io.PrintWriter;

public class CustomFilter extends AccessControlFilter {

    //访问资源之前确定是否允许访问。如果返回true，则允许访问，继续进行；如果放回false，则会调用onAccessDenied方法
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

//        String token="";
//        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
//             token = httpServletRequest.getHeader("AccessToken");
//            if (StringUtils.isEmpty(token)) {
//                token = httpServletRequest.getParameter("AccessToken");
//            }
//        }

        String accessToken = httpServletRequest.getHeader("AccessToken");
        String refreshToken = httpServletRequest.getHeader("RefreshToken");
        if(StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(refreshToken)) return false;
        CustomUsernamePasswordToken customUsernamePasswordToken = new CustomUsernamePasswordToken(accessToken,refreshToken);
        getSubject(servletRequest,servletResponse).login(customUsernamePasswordToken);
        return true;
    }

    //当isAccessAllowed方法返回false时，此方法被调用。在这里可以实现访问被拒绝时的处理
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        DataResult dataResult = new DataResult<>(ResponseCode.NO_TOKEN);
        String jsonString = JSON.toJSONString(dataResult);
        System.out.println(jsonString);
        writer.print(jsonString);
        writer.close();
        return false;
    }
}
