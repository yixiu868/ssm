package com.ww.spring.mybatis.service;

import java.util.List;

import com.ww.spring.model.Role;

public interface RoleService {

    int insertRole(Role role);
    
    int insertRoleList(List<Role> roleList);
    
    Role getRole(Long id);
    
    int updateRole(Role role);
    
    int deleteRole(Long id);
    
    List<Role> queryList(List<Long> idList);
    
    int updateBatch(List<Role> roleList);
}
