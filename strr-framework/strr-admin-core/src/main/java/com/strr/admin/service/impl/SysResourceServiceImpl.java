package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.model.SysResource;
import com.strr.admin.service.SysResourceService;
import com.strr.base.mapper.SCrudMapper;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class SysResourceServiceImpl extends SCrudServiceImpl<SysResource, Integer> implements SysResourceService {
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
     * 权限列表
     */
    @Override
    public List<SysResource> listByParam(SysResource param) {
        return sysResourceMapper.listByParam(param);
    }

    /**
     * 获取用户权限
     */
    @Override
    public List<SysResource> listByUserId(Integer userId) {
        return sysResourceMapper.listByUserId(userId);
    }

    /**
     * 删除权限
     */
    @Override
    public void removeInfo(Integer id) {
        sysRelRoleResourceMapper.removeByResourceId(id);
        sysResourceMapper.remove(id);
    }
}
