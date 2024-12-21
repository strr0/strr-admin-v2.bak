package com.strr.system.mapper;

import com.strr.base.mapper.CrudMapper;
import com.strr.system.model.SysResource;

import java.util.List;

public interface SysResourceMapper extends CrudMapper<SysResource, Integer> {
    /**
     * 获取用户权限
     */
    List<SysResource> listByUserId(Integer userId);
}
