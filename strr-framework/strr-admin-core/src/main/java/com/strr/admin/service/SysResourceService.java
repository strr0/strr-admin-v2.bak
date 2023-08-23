package com.strr.admin.service;

import com.strr.admin.model.SysResource;
import com.strr.base.service.SCrudService;

import java.util.List;

public interface SysResourceService extends SCrudService<SysResource, Integer> {
    /**
     * 权限列表
     */
    List<SysResource> listByParam(SysResource param);

    /**
     * 获取用户权限
     */
    List<SysResource> listByUserId(Integer userId);

    /**
     * 删除权限
     */
    void removeInfo(Integer id);
}
