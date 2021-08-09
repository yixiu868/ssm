package com.ww.spring.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ww.spring.model.Role;

@Repository
public interface RoleMapper {

    int insertRole(Role role);
    
    Role getRole(@Param("id") Long id);
    
    int updateRole(Role role);
    
    int deleteRole(@Param("id") Long id);
    
    List<Role> queryList(@Param("idList") List<Long> idList);
}
