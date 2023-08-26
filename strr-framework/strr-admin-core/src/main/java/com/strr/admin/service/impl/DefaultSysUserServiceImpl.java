package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRelUserRoleMapper;
import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.model.SysUser;
import com.strr.admin.model.dto.SysUserDTO;
import com.strr.admin.service.SysUserService;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.Date;
import java.util.List;

public abstract class DefaultSysUserServiceImpl extends SCrudServiceImpl<SysUser, Integer> implements SysUserService {
    private final SysUserMapper sysUserMapper;
    private final SysRelUserRoleMapper sysRelUserRoleMapper;

    public DefaultSysUserServiceImpl(SysUserMapper sysUserMapper, SysRelUserRoleMapper sysRelUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRelUserRoleMapper = sysRelUserRoleMapper;
    }

    @Override
    protected SysUserMapper getMapper() {
        return sysUserMapper;
    }

    /**
     * 获取登录用户id
     */
    protected abstract Integer getLoginUserId();

    /**
     * 保存用户
     */
    @Override
    public void saveInfo(SysUserDTO sysUser) {
        if (sysUser.getId() == null) {
            sysUser.setCreator(getLoginUserId());
            sysUser.setCreateTime(new Date());
            sysUser.setPassword("$2a$10$LBfxhQw8tw6a1eENVgk5l.mcmcM5LqAB4XIUF5BlNESO50Nq/WQ5S");  // abc123
            sysUserMapper.save(sysUser);
        } else {
            sysRelUserRoleMapper.removeByUserId(sysUser.getId());
            sysUser.setUpdator(getLoginUserId());
            sysUser.setUpdateTime(new Date());
            sysUserMapper.update(sysUser);
        }
        sysRelUserRoleMapper.batchSave(sysUser.getId(), sysUser.getRoleIds());
    }

    /**
     * 获取用户角色
     */
    @Override
    public List<Integer> listRoleId(Integer userId) {
        return sysRelUserRoleMapper.listByUserId(userId);
    }

    /**
     * 删除用户
     */
    @Override
    public void removeInfo(Integer id) {
        sysRelUserRoleMapper.removeByUserId(id);
        sysUserMapper.remove(id);
    }
}
