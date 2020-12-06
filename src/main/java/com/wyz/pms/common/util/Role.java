package com.wyz.pms.common.util;

import java.util.ArrayList;
import java.util.List;

public class Role {

    /*
     * 角色id
     */
    private Integer id;

    /*
     * 角色名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public Role setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }



    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
