package com.ww.spring.aop.aspectj;

import org.springframework.stereotype.Component;

import com.ww.spring.ioc.bean.Role;

@Component
public class RoleServiceImpl implements RoleService {

    @Override
    public void printRole(Role role) {
        System.out.println("roleServiceImpl");
        System.out.println(role.getRoleName() + "-->>>>>>>" + role);
    }

}
