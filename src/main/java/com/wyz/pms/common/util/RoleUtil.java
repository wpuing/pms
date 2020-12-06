package com.wyz.pms.common.util;

import org.apache.commons.lang3.StringUtils;

public class RoleUtil {

    public static  final Role[] roles={
            new Role().setId(0).setName("管理员"),
            new Role().setId(1).setName("安保员"),
            new Role().setId(2).setName("清洁员"),
            new Role().setId(3).setName("维修员")
    };

    public static String getRoleName(Integer id) {
        for (Role role: roles) {
            if(id!=null && role.getId()==id){
                return role.getName();
            }
        }
        return null;
    }

    public static Integer getRoleName(String name) {
        StringUtils.isEmpty(name);
        for (Role role: roles) {
            if(!StringUtils.isEmpty(name)&&role.getName().equals(name)){
                return role.getId();
            }
        }
        return null;
    }
}
