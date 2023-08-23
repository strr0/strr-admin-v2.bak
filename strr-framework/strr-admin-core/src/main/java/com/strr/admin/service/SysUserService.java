package com.strr.admin.service;

import com.strr.admin.model.SysUser;
import com.strr.admin.model.dto.SysUserDTO;
import com.strr.base.service.SCrudService;

import java.util.List;

public interface SysUserService extends SCrudService<SysUser, Integer> {
    /**
     * 保存用户
     */
    void saveInfo(SysUserDTO sysUser);

    /**
     * 获取用户角色
     */
    List<Integer> listRoleId(Integer userId);

    /**
     * 删除用户
     */
    void removeInfo(Integer id);
}
