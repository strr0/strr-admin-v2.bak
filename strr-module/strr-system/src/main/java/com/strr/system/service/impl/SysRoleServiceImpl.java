package com.strr.system.service.impl;

import com.strr.base.service.impl.CrudServiceImpl;
import com.strr.security.util.LoginUtil;
import com.strr.system.mapper.SysRelRoleResourceMapper;
import com.strr.system.mapper.SysRelUserRoleMapper;
import com.strr.system.mapper.SysRoleMapper;
import com.strr.system.model.SysRole;
import com.strr.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl extends CrudServiceImpl<SysRole, Integer> implements ISysRoleService {
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
     * 新增角色
     */
    @Override
    public int save(SysRole entity) {
        entity.setCreator(LoginUtil.getLoginUserId());
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    /**
     * 更新角色
     */
    @Override
    public int update(SysRole entity) {
        entity.setUpdator(LoginUtil.getLoginUserId());
        entity.setUpdateTime(new Date());
        return super.update(entity);
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
