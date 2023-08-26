package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.model.SysResource;
import com.strr.admin.service.SysResourceService;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.Date;
import java.util.List;

public abstract class SysResourceServiceImpl extends SCrudServiceImpl<SysResource, Integer> implements SysResourceService {
    private final SysResourceMapper sysResourceMapper;
    private final SysRelRoleResourceMapper sysRelRoleResourceMapper;

    public SysResourceServiceImpl(SysResourceMapper sysResourceMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        this.sysResourceMapper = sysResourceMapper;
        this.sysRelRoleResourceMapper = sysRelRoleResourceMapper;
    }

    @Override
    protected SysResourceMapper getMapper() {
        return sysResourceMapper;
    }

    /**
     * 获取登录用户id
     */
    protected abstract Integer getLoginUserId();

    /**
     * 新增资源
     */
    @Override
    public int save(SysResource entity) {
        entity.setCreator(getLoginUserId());
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    /**
     * 更新资源
     */
    @Override
    public int update(SysResource entity) {
        entity.setUpdator(getLoginUserId());
        entity.setUpdateTime(new Date());
        return super.update(entity);
    }

    /**
     * 资源列表
     */
    @Override
    public List<SysResource> listByParam(SysResource param) {
        return sysResourceMapper.listByParam(param);
    }

    /**
     * 获取用户资源
     */
    @Override
    public List<SysResource> listByUserId(Integer userId) {
        return sysResourceMapper.listByUserId(userId);
    }

    /**
     * 删除资源
     */
    @Override
    public void removeInfo(Integer id) {
        sysRelRoleResourceMapper.removeByResourceId(id);
        sysResourceMapper.remove(id);
    }
}
