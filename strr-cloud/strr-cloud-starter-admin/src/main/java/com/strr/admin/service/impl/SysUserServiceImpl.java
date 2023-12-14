package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.util.LoginUtil;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends DefaultSysUserServiceImpl {
    public SysUserServiceImpl(SysUserMapper sysUserMapper, SysRelUserRoleMapper sysRelUserRoleMapper) {
        super(sysUserMapper, sysRelUserRoleMapper);
    }

    @Override
    protected Integer getLoginUserId() {
        return LoginUtil.getLoginUserId();
    }
}
