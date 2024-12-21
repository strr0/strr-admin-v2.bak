package com.strr.system.controller;

import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.system.model.SysUser;
import com.strr.system.model.dto.SysUserDTO;
import com.strr.system.service.ISysUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/sysUser")
public class SysUserController extends SCrudController<SysUser, Integer> {
    private final ISysUserService sysUserService;

    public SysUserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    protected ISysUserService getService() {
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

    /**
     * 获取当前登录用户
     */
    @GetMapping("/getUserInfo")
    public Result<Map<String, Object>> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return Result.ok(jwt.getClaims());
    }
}
