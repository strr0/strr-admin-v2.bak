package com.strr.system.service;

import com.strr.base.service.CrudService;
import com.strr.system.model.SysRole;

import java.util.List;

public interface ISysRoleService extends CrudService<SysRole, Integer> {
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
