package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.model.SysResource;
import com.strr.admin.service.SysResourceService;
import com.strr.base.mapper.SCrudMapper;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class SysResourceServiceImpl extends SCrudServiceImpl<SysResource, Integer> implements SysResourceService {
    private final SysResourceMapper sysResourceMapper;

    public SysResourceServiceImpl(SysResourceMapper sysResourceMapper) {
        this.sysResourceMapper = sysResourceMapper;
    }

    @Override
    protected SCrudMapper<SysResource, Integer> getMapper() {
        return sysResourceMapper;
    }

    /**
     * 权限列表
     * @param param
     * @return
     */
    @Override
    public List<SysResource> listByParam(SysResource param) {
        return sysResourceMapper.listByParam(param);
    }

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    @Override
    public List<SysResource> listByUserId(Integer userId) {
        return sysResourceMapper.listByUserId(userId);
    }

    /**
     * 删除权限
     * @param id
     */
    @Override
    public void removeWithRel(Integer id) {
        sysResourceMapper.remove(id);
        sysResourceMapper.removeRelByRsid(id);
    }
}
