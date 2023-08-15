package com.strr.admin.mapper;

import com.strr.admin.model.SysProperties;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPropertiesMapper {
    /**
     * 新增
     */
    int save(SysProperties entity);

    /**
     * 批量新增
     */
    int batchSave(@Param("list") List<SysProperties> list);

    /**
     * 修改
     */
    int update(SysProperties entity);

    /**
     * 删除
     */
    int remove(Integer id);

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
