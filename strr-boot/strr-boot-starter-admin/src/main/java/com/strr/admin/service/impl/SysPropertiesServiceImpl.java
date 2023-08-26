package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysPropertiesMapper;
import org.springframework.stereotype.Service;

@Service
public class SysPropertiesServiceImpl extends DefaultSysPropertiesServiceImpl {
    public SysPropertiesServiceImpl(SysPropertiesMapper sysPropertiesMapper) {
        super(sysPropertiesMapper);
    }
}
