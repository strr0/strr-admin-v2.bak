package com.strr.admin.service;

import com.strr.admin.model.SysProperties;
import com.strr.base.service.SCrudService;

import java.util.List;

public interface SysPropertiesService extends SCrudService<SysProperties, Integer> {
    /**
     * 批量保存
     */
    int batchSave(List<SysProperties> list);

    /**
     * 批量删除
     */
    int batchRemove(String application);

    /**
     * 获取应用列表
     */
    List<SysProperties> listApplication(String application);

    /**
     * 获取属性列表
     */
    List<SysProperties> listProperties(String application);
}
