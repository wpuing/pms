package com.wyz.pms.common.interceptor;

import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.pojo.Owner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  @author: PUING
 *  @Date: 2020/12/10 9:04
 *  @Description: 登录验证类
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("拦截了，下面开始决定是否放行" + handler);
//        System.out.println("url+"+request.getRequestURI());
//        System.out.println("拦截了，下面开始决定是否放行" + handler);
//        System.out.println("url+"+request.getRequestURI());
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            System.out.println("没有登录，跳转到登录页面");
            response.sendRedirect("/login");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
