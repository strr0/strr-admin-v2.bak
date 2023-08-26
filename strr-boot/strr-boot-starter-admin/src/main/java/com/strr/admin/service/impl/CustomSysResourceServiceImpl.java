package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelRoleResourceMapper;
import com.strr.admin.mapper.SysResourceMapper;
import com.strr.admin.util.CustomSysUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomSysResourceServiceImpl extends SysResourceServiceImpl {
    public CustomSysResourceServiceImpl(SysResourceMapper sysResourceMapper, SysRelRoleResourceMapper sysRelRoleResourceMapper) {
        super(sysResourceMapper, sysRelRoleResourceMapper);
    }

    @Override
    protected Integer getLoginUserId() {
        return CustomSysUtil.getLoginUserId();
    }
}
