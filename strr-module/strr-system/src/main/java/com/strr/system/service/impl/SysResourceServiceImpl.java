package com.strr.system.service.impl;

import com.strr.base.service.impl.CrudServiceImpl;
import com.strr.security.util.LoginUtil;
import com.strr.system.mapper.SysRelRoleResourceMapper;
import com.strr.system.mapper.SysResourceMapper;
import com.strr.system.model.SysResource;
import com.strr.system.service.ISysResourceService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysResourceServiceImpl extends CrudServiceImpl<SysResource, Integer> implements ISysResourceService {
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
     * 新增资源
     */
    @Override
    public int save(SysResource entity) {
        entity.setCreator(LoginUtil.getLoginUserId());
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    /**
     * 更新资源
     */
    @Override
    public int update(SysResource entity) {
        entity.setUpdator(LoginUtil.getLoginUserId());
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
