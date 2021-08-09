package com.ww.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    /**
     * 返回页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public ModelAndView getRole(@RequestParam("id") Long id) {
        Role role = roleService.getRole(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("roleDetails");
        mv.addObject("role", role);
        
        return mv;
    }
    
    /**
     * 返回json格式
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRole2", method = RequestMethod.GET)
    public ModelAndView getRole2(@RequestParam("id") Long id) {
        Role role = roleService.getRole(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("roleDetails");
        mv.addObject("role", role);
        
        // 指定视图类型
        // 由于MappingJackson2JsonView是一个非逻辑视图，因此对于它而言并不需要视图解析器进行解析
        mv.setView(new MappingJackson2JsonView());
        
        return mv;
    }
}
