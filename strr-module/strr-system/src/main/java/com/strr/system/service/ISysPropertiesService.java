package com.strr.system.service;

import com.strr.base.service.CrudService;
import com.strr.system.model.SysProperties;

import java.util.List;

public interface ISysPropertiesService extends CrudService<SysProperties, Integer> {
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
