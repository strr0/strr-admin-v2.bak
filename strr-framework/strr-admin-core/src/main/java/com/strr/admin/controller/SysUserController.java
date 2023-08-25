package com.strr.admin.controller;

import com.strr.admin.model.SysUser;
import com.strr.admin.model.dto.SysUserDTO;
import com.strr.admin.service.SysUserService;
import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.base.service.SCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SysUserController extends SCrudController<SysUser, Integer> {
    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    protected SysUserService getService() {
        return sysUserService;
    }

    /**
     * 保存用户信息
     */
    @PostMapping("/saveInfo")
    public Result<Void> saveInfo(SysUserDTO sysUser) {
        sysUserService.saveInfo(sysUser);
        return Result.ok();
    }

    /**
     * 获取用户角色
     */
    @GetMapping("/listRoleId")
    public Result<List<Integer>> listRoleId(Integer userId) {
        List<Integer> data = sysUserService.listRoleId(userId);
        return Result.ok(data);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/removeInfo")
    public Result<Void> removeInfo(Integer id) {
        sysUserService.removeInfo(id);
        return Result.ok();
    }
}
