package com.wyz.pms.common.config;

import com.wyz.pms.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/login",            //登录
                "/doLogin/**",            //登录验证
                "/logout",            //退出
                "/file/**",            //上传文件
                "/static/**/*.html",            //html静态资源
                "/static/**/*.js",              //js静态资源
                "/static/**/*.css",             //css静态资源
                "/static/**/*.ico",
                "/static/**/*.jpg",
                "/static/**/*.png",
                "/static/**/*.ttf"
        );
    }

}
