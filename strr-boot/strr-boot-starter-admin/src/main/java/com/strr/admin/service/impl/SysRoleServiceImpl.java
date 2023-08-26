package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysRoleMapper;
import com.strr.admin.util.CustomSysUtil;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends DefaultSysRoleServiceImpl {
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysRelUserRoleMapper sysRelUserRoleMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        super(sysRoleMapper, sysRelUserRoleMapper, sysRelRoleResourceMapper);
    }

    @Override
    protected Integer getLoginUserId() {
        return CustomSysUtil.getLoginUserId();
    }
}