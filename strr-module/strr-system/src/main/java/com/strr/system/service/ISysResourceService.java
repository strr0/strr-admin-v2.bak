package com.strr.system.service;

import com.strr.base.service.CrudService;
import com.strr.system.model.SysResource;

import java.util.List;

public interface ISysResourceService extends CrudService<SysResource, Integer> {
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
