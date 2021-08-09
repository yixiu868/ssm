package com.ww.spring.aop.aspectj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ww.spring.model.Role;

public class AspectJAopTest {

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        RoleService roleService = context.getBean(RoleService.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("role_name_1");
        role.setNote("note_1");
        roleService.printRole(role);
        
//        System.out.println("-------------");
//        role = null;
//        roleService.printRole(role);
    }
}
