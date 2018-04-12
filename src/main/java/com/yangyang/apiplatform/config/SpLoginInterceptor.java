package com.yangyang.apiplatform.config;

import com.yangyang.apiplatform.entity.Sp;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SpLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("执行到了拦截方法");
        HttpSession session=httpServletRequest.getSession();
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getServletPath());
        System.out.println(httpServletRequest.getPathInfo());
        String path=httpServletRequest.getServletPath();
if(path.equals("/consumer_login")||path.equals("/consumer_register")||path.equals("/consumer_loginpage")||path.equals("/sp_login")||path.equals("/sp_register")||path.equals("/sp_loginpage")){
    System.out.println("注册或者登陆界面，放行");
    return true;
}else{
    System.out.println("其他界面");
    return true;
}

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
