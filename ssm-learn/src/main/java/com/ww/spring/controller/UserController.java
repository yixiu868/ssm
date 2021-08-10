package com.ww.spring.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ww.spring.model.User;

/**
 * @Description: 拦截器实现用户登录权限验证
 * @author xiaohua
 * @date 2021年8月10日 下午2:52:48
 */
@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /**
     * 向用户展示登录页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        logger.info("用户从login的请求到登录跳转login.jsp");
        return "login";
    }
    
    /**
     * 用户实现登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        String userName = user.getUserName();
        String password = user.getPassword();
        
        if (StringUtils.equals(userName, "wanggw") && StringUtils.equals(password, "123456")) {
            logger.info("用户{}登录成功", userName);
            // 将用户添加至session
            session.setAttribute("user", user);
            // 重定向到主页
            return "redirect:/user/index.do";
        }
        
        model.addAttribute("message", "账号或密码错误，请重新登录");
        return "login";
    }
    
    /**
     * 跳转至主页
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage() {
        logger.info("跳转主页");
        return "index";
    }
    
    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 清除session
        session.invalidate();
        logger.info("退出功能实现，清除session，重定向到login请求");
        return "redirect:/user/login.do";
    }
}
