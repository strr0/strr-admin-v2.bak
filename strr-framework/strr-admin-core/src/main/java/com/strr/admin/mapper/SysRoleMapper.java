package com.strr.admin.mapper;

import com.strr.admin.model.SysRole;
import com.strr.base.mapper.SCrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends SCrudMapper<SysRole, Integer> {
    /**
     * 添加角色权限
     */
    int saveRel(@Param("rid") Integer rid, @Param("rsid") Integer rsid);

    /**
     * 删除角色权限
     */
    int removeRel(@Param("rid") Integer rid, @Param("rsid") Integer rsid);

    /**
     * 获取权限id
     * @param rid
     * @return
     */
    List<Integer> listRelByRid(Integer rid);

    /**
     * 删除角色权限关联
     * @param rid
     * @return
     */
    int removeRRRelByRid(Integer rid);

    /**
     * 删除用户角色关联
     * @param rid
     * @return
     */
    int removeURRelByRid(Integer rid);
}
