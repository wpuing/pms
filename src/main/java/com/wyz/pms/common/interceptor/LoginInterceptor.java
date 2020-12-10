package com.wyz.pms.common.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  @author: PUING
 *  @Date: 2020/12/10 9:04
 *  @Description: 登录验证类
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截了，下面开始决定是否放行" + handler);
        System.out.println("url+"+request.getRequestURI());
        System.out.println("拦截了，下面开始决定是否放行" + handler);
//        System.out.println("url+"+request.getRequestURI());
//        if(request.getRequestURI().contains("login")){
//            return true;
//        }
//        String username = (String) request.getSession().getAttribute("user");
//        if (username == null) {
//            System.out.println("没有登录，跳转到登录页面");
//            response.sendRedirect("/login");
//            return false;
//        } else {
//            return true;
//        }
        return super.preHandle(request, response, handler);
    }
}
