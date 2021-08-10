package com.ww.spring.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Description: 登录拦截器
 * @author xiaohua
 * @date 2021年8月10日 下午3:03:48
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // 非登录请求
        if (!StringUtils.contains(uri, "login")) {
            // session已登录
            if (Objects.nonNull(request.getSession().getAttribute("user"))) {
                return true;
            } else {
                request.setAttribute("message", "请先登录");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } else {
            return true;
        }
        
        return false;
    }
}
