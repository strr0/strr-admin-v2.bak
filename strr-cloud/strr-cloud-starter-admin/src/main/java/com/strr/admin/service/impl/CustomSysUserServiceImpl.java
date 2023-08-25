package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.model.dto.SysUserDTO;
import com.strr.admin.util.CustomSysUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomSysUserServiceImpl extends SysUserServiceImpl {
    public CustomSysUserServiceImpl(SysUserMapper sysUserMapper, SysRelUserRoleMapper sysRelUserRoleMapper) {
        super(sysUserMapper, sysRelUserRoleMapper);
    }

    @Override
    public void saveInfo(SysUserDTO sysUser) {
        Integer userId = CustomSysUtil.getLoginUserId();
        if (sysUser.getId() == null) {
            sysUser.setCreator(userId);
            sysUser.setCreateTime(new Date());
            sysUser.setPassword("$2a$10$LBfxhQw8tw6a1eENVgk5l.mcmcM5LqAB4XIUF5BlNESO50Nq/WQ5S");  // abc123
            getMapper().save(sysUser);
        } else {
            sysUser.setUpdator(userId);
            sysUser.setUpdateTime(new Date());
            getSysRelUserRoleMapper().removeByUserId(sysUser.getId());
            getMapper().update(sysUser);
        }
        getSysRelUserRoleMapper().batchSave(sysUser.getId(), sysUser.getRoleIds());
    }
}
