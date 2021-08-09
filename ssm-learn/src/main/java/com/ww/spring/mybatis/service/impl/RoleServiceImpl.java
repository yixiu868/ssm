package com.ww.spring.mybatis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ww.spring.model.Role;
import com.ww.spring.mybatis.mapper.RoleMapper;
import com.ww.spring.mybatis.service.RoleService;

/**
 * @Description: 注解失效
 * 
 * 自调用
 * 自己调用自己的过程中，不存在代理对象的调用，这样不会产生AOP去配置@Transactional参数，造成注解失效
 * 
 * @author xiaohua
 * @date 2021年8月9日 上午9:22:40
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public int insertRole(Role role) {
        logger.info("单个插入role:{}", JSON.toJSONString(role));
        return roleMapper.insertRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        int count = 0;
        
        for (Role role : roleList) {
            try {
                // 调用自身类的方法，产生自调用，@Transactional注解失效
                insertRole(role);
                count++;
            } catch (Exception e) {
                logger.error("批量插入role异常：{}", e.getMessage(), e);
            }
        }
        
        return count;
    }

    @Override
    public Role getRole(Long id) {
        return roleMapper.getRole(id);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int deleteRole(Long id) {
        return roleMapper.deleteRole(id);
    }

    @Override
    public List<Role> queryList(List<Long> idList) {
        return roleMapper.queryList(idList);
    }

}
