package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysPropertiesMapper;
import com.strr.admin.model.SysProperties;
import com.strr.admin.service.SysPropertiesService;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class DefaultSysPropertiesServiceImpl extends SCrudServiceImpl<SysProperties, Integer> implements SysPropertiesService {
    private final SysPropertiesMapper sysPropertiesMapper;

    public DefaultSysPropertiesServiceImpl(SysPropertiesMapper sysPropertiesMapper) {
        this.sysPropertiesMapper = sysPropertiesMapper;
    }

    @Override
    protected SysPropertiesMapper getMapper() {
        return sysPropertiesMapper;
    }

    /**
     * 批量保存
     */
    @Override
    public int batchSave(List<SysProperties> list) {
        return sysPropertiesMapper.batchSave(list);
    }

    /**
     * 批量删除
     */
    @Override
    public int batchRemove(String application) {
        return sysPropertiesMapper.batchRemove(application);
    }

    /**
     * 获取应用列表
     */
    @Override
    public List<SysProperties> listApplication(String application) {
        return sysPropertiesMapper.listApplication(application);
    }

    /**
     * 获取属性列表
     */
    @Override
    public List<SysProperties> listProperties(String application) {
        return sysPropertiesMapper.listProperties(application);
    }
}
