package com.strr.admin.mapper;

import com.strr.admin.model.SysUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    /**
     * 获取用户
     * @param username
     * @return
     */
    List<SysUserDetails> getByUsername(String username);
}
