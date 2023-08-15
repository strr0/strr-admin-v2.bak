package com.strr.admin.mapper;

import com.strr.admin.model.SysRole;
import com.strr.admin.model.SysUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDetailsMapper {
    /**
     * 获取用户
     * @param username
     * @return
     */
    List<SysUserDetails> getByUsername(String username);

    /**
     * 获取角色
     * @param userId
     * @return
     */
    List<SysRole> listRoleByUserId(Integer userId);
}
