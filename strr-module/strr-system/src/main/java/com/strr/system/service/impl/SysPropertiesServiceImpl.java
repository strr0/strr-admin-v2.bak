package com.strr.system.service.impl;

import com.strr.base.service.impl.CrudServiceImpl;
import com.strr.system.mapper.SysPropertiesMapper;
import com.strr.system.model.SysProperties;
import com.strr.system.service.ISysPropertiesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPropertiesServiceImpl extends CrudServiceImpl<SysProperties, Integer> implements ISysPropertiesService {
    private final SysPropertiesMapper sysPropertiesMapper;

    public SysPropertiesServiceImpl(SysPropertiesMapper sysPropertiesMapper) {
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
