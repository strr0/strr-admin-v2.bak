package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysResourceMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomSysResourceServiceImpl extends SysResourceServiceImpl {
    public CustomSysResourceServiceImpl(SysResourceMapper sysResourceMapper) {
        super(sysResourceMapper);
    }
}
