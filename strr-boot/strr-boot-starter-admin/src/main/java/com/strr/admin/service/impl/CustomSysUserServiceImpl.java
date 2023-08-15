package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysUserMapper;
import com.strr.admin.model.SysUser;
import com.strr.admin.util.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomSysUserServiceImpl extends SysUserServiceImpl {
    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomSysUserServiceImpl(SysUserMapper sysUserMapper, PasswordEncoder passwordEncoder) {
        super(sysUserMapper);
        this.sysUserMapper = sysUserMapper;
        this.passwordEncoder = passwordEncoder;
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
            sysUser.setPassword(passwordEncoder.encode("abc123"));
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
}
