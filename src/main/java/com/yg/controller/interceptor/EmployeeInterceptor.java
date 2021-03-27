package com.yg.controller.interceptor;

import com.yg.constant.WebConstant;
import org.springframework.web.servlet.HandlerInterceptor;

public class EmployeeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        //获取HttpSession中的level属性
        String level = (String)request.getSession().getAttribute(WebConstant.LEVEL);
        if(level != null && (level.equals(WebConstant.EMP_LEVEL)
        || level.equals(WebConstant.MGR_LEVEL))){
            return true;
        }
        //如果用户没有登录，则设置提示信息，跳转到登录页面
        request.setAttribute("tip","请先登录再使用本系统功能");
        request.getRequestDispatcher("login").forward(request,response);
        //返回false，不再执行后续处理
        return false;
    }
}
