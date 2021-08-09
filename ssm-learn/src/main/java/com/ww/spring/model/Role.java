package com.ww.spring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 注解装载Bean
 * 
 * 注解@Value代表的是值的注入，这里只是简单注入一些值，其中id是一个Long型，注入的时候Spring会为其转化类型。
 * 
 * @author xiaohua
 * @date 2021年8月7日 下午4:39:57
 */
//@Component(value = "role")
public class Role {

//    @Value("1")
    private Long id;
    
//    @Value("role_name_1")
    private String roleName;
    
//    @Value("role_note_1")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", roleName=" + roleName + ", note=" + note + "]";
    }
}
