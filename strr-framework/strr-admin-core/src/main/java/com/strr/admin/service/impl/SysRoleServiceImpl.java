package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysRoleMapper;
import com.strr.admin.model.SysRole;
import com.strr.admin.service.SysRoleService;
import com.strr.base.mapper.SCrudMapper;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class SysRoleServiceImpl extends SCrudServiceImpl<SysRole, Integer> implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;
    private final SysRelUserRoleMapper sysRelUserRoleMapper;
    private final SysRelRoleResourceMapper sysRelRoleResourceMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysRelUserRoleMapper sysRelUserRoleMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysRelUserRoleMapper = sysRelUserRoleMapper;
        this.sysRelRoleResourceMapper = sysRelRoleResourceMapper;
    }

    @Override
    protected SysRoleMapper getMapper() {
        return sysRoleMapper;
    }

    /**
     * 获取角色列表
     */
    @Override
    public List<SysRole> listByParam(SysRole param) {
        return sysRoleMapper.listByParam(param);
    }

    /**
     * 更新角色权限
     */
    @Override
    public void updateRel(Integer roleId, Integer[] resourceIds) {
        sysRelRoleResourceMapper.removeByRoleId(roleId);
        sysRelRoleResourceMapper.batchSave(roleId, resourceIds);
    }

    /**
     * 获取角色权限
     */
    @Override
    public List<Integer> listResourceId(Integer roleId) {
        return sysRelRoleResourceMapper.listByRoleId(roleId);
    }

    /**
     * 删除角色
     */
    @Override
    public void removeInfo(Integer id) {
        sysRelUserRoleMapper.removeByRoleId(id);
        sysRelRoleResourceMapper.removeByRoleId(id);
        sysRoleMapper.remove(id);
    }
}
