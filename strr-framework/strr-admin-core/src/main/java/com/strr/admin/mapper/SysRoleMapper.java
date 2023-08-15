package com.strr.admin.mapper;

import com.strr.admin.model.SysRole;
import com.strr.base.mapper.SCrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends SCrudMapper<SysRole, Integer> {
    /**
     * 添加角色权限
     * @param rid
     * @param aid
     * @return
     */
    int saveRel(@Param("rid") Integer rid, @Param("aid") Integer aid);

    /**
     * 删除角色权限
     * @param rid
     * @param aid
     * @return
     */
    int removeRel(@Param("rid") Integer rid, @Param("aid") Integer aid);

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
    int removeRARelByRid(Integer rid);

    /**
     * 删除用户角色关联
     * @param rid
     * @return
     */
    int removeURRelByRid(Integer rid);
}
