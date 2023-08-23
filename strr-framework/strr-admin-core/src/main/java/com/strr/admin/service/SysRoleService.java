package com.strr.admin.service;

import com.strr.admin.model.SysRole;
import com.strr.base.service.SCrudService;

import java.util.List;

public interface SysRoleService extends SCrudService<SysRole, Integer> {
    /**
     * 获取角色列表
     */
    List<SysRole> listByParam(SysRole param);

    /**
     * 更新角色权限
     */
    void updateRel(Integer roleId, Integer[] resourceIds);

    /**
     * 获取角色权限
     */
    List<Integer> listResourceId(Integer roleId);

    /**
     * 删除角色
     */
    void removeInfo(Integer id);
}
