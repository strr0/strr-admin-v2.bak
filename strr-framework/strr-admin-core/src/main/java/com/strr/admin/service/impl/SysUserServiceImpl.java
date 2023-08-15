package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.model.SysUser;
import com.strr.admin.service.SysUserService;
import com.strr.admin.util.SysUtil;
import com.strr.base.mapper.SCrudMapper;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class SysUserServiceImpl extends SCrudServiceImpl<SysUser, Integer> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    protected SCrudMapper<SysUser, Integer> getMapper() {
        return sysUserMapper;
    }

    /**
     * 保存用户
     * @param sysUser
     * @param oldRids
     * @param newRids
     */
    @Override
    public void saveWithRel(SysUser sysUser, Integer[] oldRids, Integer[] newRids) {
        if (sysUser.getId() == null) {
            sysUser.setPassword("$2a$10$LBfxhQw8tw6a1eENVgk5l.mcmcM5LqAB4XIUF5BlNESO50Nq/WQ5S");
            save(sysUser);
        } else {
            update(sysUser);
        }
        for (Integer rid : SysUtil.subtraction(oldRids, newRids)) {
            sysUserMapper.removeRel(sysUser.getId(), rid);
        }
        for (Integer rid : SysUtil.subtraction(newRids, oldRids)) {
            sysUserMapper.saveRel(sysUser.getId(), rid);
        }
    }

    /**
     * 获取用户角色
     * @param uid
     * @return
     */
    @Override
    public List<Integer> listRelByUid(Integer uid) {
        return sysUserMapper.listRelByUid(uid);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void removeWithRel(Integer id) {
        sysUserMapper.remove(id);
        sysUserMapper.removeRelByUid(id);
    }
}
