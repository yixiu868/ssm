package com.ww.spring.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.service.RoleService;

@RequestMapping("/redirect")
@Controller
public class RedirectController {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/showRoleJsonInfo")
    public ModelAndView showRoleJsonInfo(Long id, String roleName, String note) {
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        
        mv.addObject("id", id);
        mv.addObject("roleName", roleName);
        mv.addObject("note", note);
        
        return mv;
    }
    
    /**
     * Model代表的是一个数据模型，你可以给它附上对应的数据模型，然后通过返回字符串来实现重定向的功能
     * Sprinv MVC有一个约定，当返回的字符串带有redirect的时候，它就会认为需要的是一个重定向；
     * 不仅可以通过返回字符串来实现重定向，也可以通过返回视图来实现重定向。
     * @param model
     * @param roleName
     * @param note
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/addRole")
    public String addRole(Model model, String roleName, String note) throws UnsupportedEncodingException {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setNote(note);
        
        roleService.insertRole(role);
        
        // 绑定重定向数据模型
        model.addAttribute("roleName", roleName);
        model.addAttribute("note", note);
        model.addAttribute("id", role.getId());
        
        return "redirect:./showRoleJsonInfo.do";
    }
    
    /**
     * 视图进行跳转
     * @param mv
     * @param roleName
     * @param note
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addRole2")
    public ModelAndView addRole2(ModelAndView mv, String roleName, String note) throws UnsupportedEncodingException {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setNote(note);
        
        roleService.insertRole(role);
        
        // 绑定重定向数据模型
        mv.addObject("roleName", roleName);
        mv.addObject("note", note);
        mv.addObject("id", role.getId());
        mv.setViewName("redirect:./showRoleJsonInfo.do");
        
        return mv;
    }
}
