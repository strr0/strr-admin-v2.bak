package com.strr.admin.model.dto;

import com.strr.admin.model.SysUser;

public class SysUserDTO extends SysUser {
    private Integer[] roleIds;

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
}
