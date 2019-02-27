package com.lss.scdool.web.filter;

import com.lss.scdool.entity.UserInfo;
import com.lss.scdool.service.UserInfoService;
import com.lss.scdool.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Magic
 * @date 11:37 2018/4/13
 * @description
 */

@Order(2)
@Component
public class AddUserInfoFilter implements Filter{

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        if(request.getServletPath().startsWith("/app")){
            String  openIdObj  = request.getHeader("token");
            if(StringUtils.isEmpty(openIdObj)){
                openIdObj = "******";
            }
            UserInfo userInfo =  userInfoService.getUserByOpenId(openIdObj);
            if(userInfo!=null){
                UserThreadLocal.getInstance().set(userInfo);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
