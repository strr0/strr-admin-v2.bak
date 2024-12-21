package com.strr.system.service.impl;

import com.strr.base.service.impl.CrudServiceImpl;
import com.strr.security.util.LoginUtil;
import com.strr.system.mapper.SysRelUserRoleMapper;
import com.strr.system.mapper.SysUserMapper;
import com.strr.system.model.SysUser;
import com.strr.system.model.dto.SysUserDTO;
import com.strr.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl extends CrudServiceImpl<SysUser, Integer> implements ISysUserService {
    private final SysUserMapper sysUserMapper;
    private final SysRelUserRoleMapper sysRelUserRoleMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper, SysRelUserRoleMapper sysRelUserRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRelUserRoleMapper = sysRelUserRoleMapper;
    }

    @Override
    protected SysUserMapper getMapper() {
        return sysUserMapper;
    }

    /**
     * 保存用户
     */
    @Override
    public void saveInfo(SysUserDTO sysUser) {
        if (sysUser.getId() == null) {
            sysUser.setCreator(LoginUtil.getLoginUserId());
            sysUser.setCreateTime(new Date());
            sysUser.setPassword("$2a$10$LBfxhQw8tw6a1eENVgk5l.mcmcM5LqAB4XIUF5BlNESO50Nq/WQ5S");  // abc123
            sysUserMapper.save(sysUser);
        } else {
            sysRelUserRoleMapper.removeByUserId(sysUser.getId());
            sysUser.setUpdator(LoginUtil.getLoginUserId());
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
