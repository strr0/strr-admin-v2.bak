package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomSysUserServiceImpl extends SysUserServiceImpl {
    public CustomSysUserServiceImpl(SysUserMapper sysUserMapper, SysRelUserRoleMapper sysRelUserRoleMapper) {
        super(sysUserMapper, sysRelUserRoleMapper);
    }
}
