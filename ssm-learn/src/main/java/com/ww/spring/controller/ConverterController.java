package com.ww.spring.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.service.RoleService;

@RequestMapping("/converter")
@Controller
public class ConverterController {

    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/updateRole")
    @ResponseBody
    public Map<String, Object> updateRole(Role role) {
        Map<String, Object> result = new HashMap<>();
        
        boolean updateFlag = (1 == roleService.updateRole(role));
        result.put("success", updateFlag);
        if (updateFlag) {
            result.put("msg", "更新成功");
        } else {
            result.put("msg", "更新失败");
        }
        
        return result;
    }
    
    @RequestMapping(value = "/updateRoleList")
    @ResponseBody
    public Map<String, Object> updateRoleList(Role[] roleArr) {
        Map<String, Object> result = new HashMap<>();
        boolean updateFlag = (roleService.updateBatch(Arrays.asList(roleArr)) > 1);
        
        result.put("success", updateFlag);
        if (updateFlag) {
            result.put("msg", "更新成功");
        } else {
            result.put("msg", "更新失败");
        }
        
        return result;
    }
}
