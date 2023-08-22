package com.strr.admin.service.impl;

import com.strr.admin.mapper.SysRoleMapper;
import com.strr.admin.model.SysRole;
import com.strr.admin.service.SysRoleService;
import com.strr.admin.util.SysUtil;
import com.strr.base.mapper.SCrudMapper;
import com.strr.base.service.impl.SCrudServiceImpl;

import java.util.List;

public class SysRoleServiceImpl extends SCrudServiceImpl<SysRole, Integer> implements SysRoleService {
    private final SysRoleMapper sysRoleMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }

    @Override
    protected SCrudMapper<SysRole, Integer> getMapper() {
        return sysRoleMapper;
    }

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public List<SysRole> list() {
        return sysRoleMapper.listByParam(null);
    }

    /**
     * 更新角色权限
     */
    @Override
    public void updateRel(Integer rid, Integer[] oldRsids, Integer[] newRsids) {
        for (Integer rsid : SysUtil.subtraction(oldRsids, newRsids)) {
            sysRoleMapper.removeRel(rid, rsid);
        }
        for (Integer rsid : SysUtil.subtraction(newRsids, oldRsids)) {
            sysRoleMapper.saveRel(rid, rsid);
        }
    }

    /**
     * 获取角色权限
     * @param rid
     * @return
     */
    @Override
    public List<Integer> listRelByRid(Integer rid) {
        return sysRoleMapper.listRelByRid(rid);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void removeWithRel(Integer id) {
        sysRoleMapper.remove(id);
        sysRoleMapper.removeRRRelByRid(id);
        sysRoleMapper.removeURRelByRid(id);
    }
}
