package com.strr.admin.mapper;

import com.strr.admin.model.SysAuthority;
import com.strr.base.mapper.SCrudMapper;

import java.util.List;

public interface SysAuthorityMapper extends SCrudMapper<SysAuthority, Integer> {
    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    List<SysAuthority> listByUserId(Integer userId);

    /**
     * 删除角色权限关联
     * @param aid
     * @return
     */
    int removeRelByAid(Integer aid);
}
