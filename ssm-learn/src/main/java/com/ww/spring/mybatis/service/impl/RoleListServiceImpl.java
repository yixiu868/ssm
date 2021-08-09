package com.ww.spring.mybatis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ww.spring.model.Role;
import com.ww.spring.mybatis.service.RoleListService;
import com.ww.spring.mybatis.service.RoleService;

@Service("roleListService")
public class RoleListServiceImpl implements RoleListService {
    
    @Autowired
    private RoleService roleService;
    
    private static final Logger logger = LoggerFactory.getLogger(RoleListServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        int count = 0;
        
        for (Role role : roleList) {
            try {
                count += roleService.insertRole(role);
            } catch (Exception e) {
                logger.error("批量新增role异常：{}", e.getMessage(), e);
            }
        }
        
        return count;
    }

}
