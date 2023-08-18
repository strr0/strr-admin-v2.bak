package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysPropertiesMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomSysPropertiesServiceImpl extends SysPropertiesServiceImpl {
    public CustomSysPropertiesServiceImpl(SysPropertiesMapper sysPropertiesMapper) {
        super(sysPropertiesMapper);
    }
}
