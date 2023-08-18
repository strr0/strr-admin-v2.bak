package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomSysRoleServiceImpl extends SysRoleServiceImpl {
    public CustomSysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        super(sysRoleMapper);
    }
}
