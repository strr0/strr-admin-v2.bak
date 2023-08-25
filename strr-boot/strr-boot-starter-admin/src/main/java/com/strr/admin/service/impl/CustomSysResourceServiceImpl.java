package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.model.SysResource;
import com.strr.admin.util.CustomSysUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomSysResourceServiceImpl extends SysResourceServiceImpl {
    public CustomSysResourceServiceImpl(SysResourceMapper sysResourceMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        super(sysResourceMapper, sysRelRoleResourceMapper);
    }

    @Override
    public int save(SysResource entity) {
        entity.setCreator(CustomSysUtil.getLoginUserId());
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public int update(SysResource entity) {
        entity.setUpdator(CustomSysUtil.getLoginUserId());
        entity.setUpdateTime(new Date());
        return super.update(entity);
    }
}
