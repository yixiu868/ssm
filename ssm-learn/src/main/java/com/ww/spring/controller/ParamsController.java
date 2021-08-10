package com.ww.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.service.RoleService;

/**
 * @Description: 练习Controller接收参数常用做法
 * @author xiaohua
 * @date 2021年8月9日 下午2:11:57
 */
@RequestMapping("/params")
@Controller
public class ParamsController {
    
    private Logger logger = LoggerFactory.getLogger(ParamsController.class);
    
    @Autowired
    private RoleService roleService;

    /**
     * 参数名称与HTTP请求参数的名称保持一致，无须任何注解也可以获取参数
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/commonParams")
    public ModelAndView commonParams(String roleName, String note) {
        logger.info("接收到roleName:{}", roleName);
        logger.info("接收到note:{}", note);
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        
        return mv;
    }
    
    /**
     * 通过pojo获取参数
     * 要求POJO的属性和HTTP参数要一一对应
     * @param role
     * @return
     */
    @RequestMapping("commonParamPojo")
    public ModelAndView commonParamPojo(Role role) {
        logger.info("接收到roleName:{}", role.getRoleName());
        logger.info("接收到note:{}", role.getNote());
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        
        return mv;
    }
    
    /**
     * 使用@RequestParam指定映射HTTP参数名称
     * @param roleName
     * @param note
     * @return
     */
    @RequestMapping("/requestParam")
    public ModelAndView requestParam(@RequestParam("role_name") String roleName, String note) {
        logger.info("接收到roleName:{}", roleName);
        logger.info("接收到note:{}", note);
        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        
        return mv;
    }
    
    /**
     * 从URL的请求地址中获取参数
     * @param id
     * @return
     */
    @RequestMapping("/getRole/{id}")
    public ModelAndView requestParam(@PathVariable("id") Long id) {
        Role role = roleService.getRole(id);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject(role);
        
        // 设置JSON视图
        mv.setView(new MappingJackson2JsonView());
        
        return mv;
    }
    
    /**
     * JSON格式传入参数
     * @param role
     * @return
     */
    @RequestMapping("/json")
    public ModelAndView requestParam(@RequestBody Role role) {
        Role roleResult = roleService.getRole(role.getId());
        
        ModelAndView mv = new ModelAndView();
        mv.addObject(roleResult);
        
        // 设置JSON视图
        mv.setView(new MappingJackson2JsonView());
        
        return mv;
    }
    
    /**
     * 接收列表参数
     * @param role
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView requestParam(@RequestBody List<Role> role) {
        
        List<Long> idList = role.stream().map(Role::getId).collect(Collectors.toList());        
        List<Role> roleList = roleService.queryList(idList);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject(roleList);
        
        // 设置JSON视图
        mv.setView(new MappingJackson2JsonView());
        
        return mv;
    }
}
