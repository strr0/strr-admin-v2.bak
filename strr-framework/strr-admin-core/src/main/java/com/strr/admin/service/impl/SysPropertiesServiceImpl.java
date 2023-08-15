package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysPropertiesMapper;
import com.strr.admin.model.SysProperties;
import com.strr.admin.service.SysPropertiesService;

import java.util.List;

public class SysPropertiesServiceImpl implements SysPropertiesService {
    private final SysPropertiesMapper sysPropertiesMapper;

    public SysPropertiesServiceImpl(SysPropertiesMapper sysPropertiesMapper) {
        this.sysPropertiesMapper = sysPropertiesMapper;
    }

    /**
     * 新增
     */
    @Override
    public int save(SysProperties entity) {
        return sysPropertiesMapper.save(entity);
    }

    /**
     * 批量保存
     */
    @Override
    public int batchSave(List<SysProperties> list) {
        return sysPropertiesMapper.batchSave(list);
    }

    /**
     * 修改
     */
    @Override
    public int update(SysProperties entity) {
        return sysPropertiesMapper.update(entity);
    }

    /**
     * 删除
     */
    @Override
    public int remove(Integer id) {
        return sysPropertiesMapper.remove(id);
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
