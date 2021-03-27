package com.yg.controller.interceptor;

import com.yg.constant.WebConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String level = (String)request.getSession().getAttribute(WebConstant.LEVEL);
        if(level != null && level.equals(WebConstant.MGR_LEVEL)){
            return true;
        }
        request.setAttribute("tip","请先以经理账号登录再使用经理的功能");
        request.getRequestDispatcher("login").forward(request,response);
        return false;
    }
}
