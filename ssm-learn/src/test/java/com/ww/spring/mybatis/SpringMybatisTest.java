package com.ww.spring.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.mapper.RoleMapper;
import com.ww.spring.mybatis.service.RoleListService;
import com.ww.spring.mybatis.service.RoleService;

/**
 * @Description: Spring集成Mybatis
 * @author xiaohua
 * @date 2021年8月8日 上午11:17:27
 */
public class SpringMybatisTest {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringMybatisTest.class);

    @SuppressWarnings("resource")
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        RoleMapper roleMapper = context.getBean(RoleMapper.class);
        Role role = new Role();
//        role.setRoleName("管理员");
//        role.setNote("备注");
//        roleMapper.insertRole(role);
        
        role.setId(1L);
        role.setRoleName("大boss");
        role.setNote("备注撒呢");
        int updateResult = roleMapper.updateRole(role);
        if (updateResult > 0) {
            logger.info("更新成功");
        }
    }
    
    /**
     * 事务测试
     */
    @SuppressWarnings("resource")
    @Test
    public void transactionTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        RoleListService roleListService = context.getBean(RoleListService.class);
        
        List<Role> roleList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setNote("note_" + i);
            roleList.add(role);
        }
        
        int count = roleListService.insertRoleList(roleList);
        logger.info("批量插入role,成功插入{}条", count);
    }
    
    /**
     * 注解失效
     */
    @SuppressWarnings("resource")
    @Test
    public void transactionInvalidTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        RoleService roleService = context.getBean(RoleService.class);
        
        List<Role> roleList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Role role = new Role();
            role.setRoleName("role_name_a" + i);
            role.setNote("note_a" + i);
            roleList.add(role);
        }
        int count = roleService.insertRoleList(roleList);
        logger.info("批量插入role,成功插入{}条", count);
    }
}
