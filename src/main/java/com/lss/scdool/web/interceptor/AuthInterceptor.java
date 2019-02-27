package com.lss.scdool.web.interceptor;

import com.lss.scdool.annotation.NoAuthentication;
import com.lss.scdool.exception.ScException;
import com.lss.scdool.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Magic
 * @date 21:49 2018/4/3
 * @description
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入权限雁阵拦截器了");
        //读取静态文件时，直接放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        NoAuthentication classAnnotation = ((HandlerMethod)handler).getBeanType().getAnnotation(NoAuthentication.class);
        NoAuthentication methodAnnotation = ((HandlerMethod)handler).getMethodAnnotation(NoAuthentication.class);
        if(classAnnotation!=null || methodAnnotation !=null){
            return true;
        }
        if(UserThreadLocal.getInstance().get()==null){
            throw new ScException("没有权限");
        }
        if(!"101".equals(UserThreadLocal.getInstance().get().getIsEnabled())){
            throw new ScException("该用户尚未激活，请等待管理员启用");
        }
        return true;
    }
}
