package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysRoleMapper;
import com.strr.admin.model.SysRole;
import com.strr.admin.util.CustomSysUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomSysRoleServiceImpl extends SysRoleServiceImpl {
    public CustomSysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysRelUserRoleMapper sysRelUserRoleMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        super(sysRoleMapper, sysRelUserRoleMapper, sysRelRoleResourceMapper);
    }

    @Override
    public int save(SysRole entity) {
        entity.setCreator(CustomSysUtil.getLoginUserId());
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public int update(SysRole entity) {
        entity.setUpdator(CustomSysUtil.getLoginUserId());
        entity.setUpdateTime(new Date());
        return super.update(entity);
    }
}
