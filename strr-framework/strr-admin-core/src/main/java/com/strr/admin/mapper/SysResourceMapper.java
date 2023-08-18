package com.strr.admin.mapper;

import com.strr.admin.model.SysResource;
import com.strr.base.mapper.SCrudMapper;

import java.util.List;

public interface SysResourceMapper extends SCrudMapper<SysResource, Integer> {
    /**
     * 获取用户权限
     */
    List<SysResource> listByUserId(Integer userId);

    /**
     * 删除角色权限关联
     */
    int removeRelByRsid(Integer rsid);
}
