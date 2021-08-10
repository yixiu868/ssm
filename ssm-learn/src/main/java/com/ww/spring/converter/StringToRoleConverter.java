package com.ww.spring.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ww.spring.model.Role;

public class StringToRoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String source) {
        // 空串
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        
        // 不包含特定字符
        if (-1 == source.indexOf("-")) {
            return null;
        }
        
        String[] arr = source.split("-");
        // 字符串长度不对
        if (3 != arr.length) {
            return null;
        }
        
        Role role = new Role();
        role.setId(Long.parseLong(arr[0]));
        role.setRoleName(arr[1]);
        role.setNote(arr[2]);
        
        return role;
    }

}
