package com.strr.admin.mapper;

import com.strr.admin.model.SysProperties;
import com.strr.base.mapper.SCrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPropertiesMapper extends SCrudMapper<SysProperties, Integer> {
    /**
     * 批量新增
     */
    int batchSave(@Param("list") List<SysProperties> list);

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
