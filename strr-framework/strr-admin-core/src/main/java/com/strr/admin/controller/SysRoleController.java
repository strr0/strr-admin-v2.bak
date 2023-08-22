package com.strr.admin.controller;

import com.strr.admin.model.SysRole;
import com.strr.admin.service.SysRoleService;
import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.base.service.SCrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SysRoleController extends SCrudController<SysRole, Integer> {
    private final SysRoleService sysRoleService;

    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Override
    protected SCrudService<SysRole, Integer> getService() {
        return sysRoleService;
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping("/list")
    public Result<List<SysRole>> list() {
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    /**
     * 更新角色权限
     */
    @PostMapping("/updateRel")
    public Result<Void> updateRel(Integer rid, Integer[] oldRsids, Integer[] newRsids) {
        sysRoleService.updateRel(rid, oldRsids, newRsids);
        return Result.ok();
    }

    /**
     * 获取角色权限
     * @param rid
     * @return
     */
    @GetMapping("/listRelByRid")
    public Result<List<Integer>> listRelByRid(Integer rid) {
        List<Integer> data = sysRoleService.listRelByRid(rid);
        return Result.ok(data);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/removeInfo")
    public Result<Void> removeInfo(Integer id) {
        sysRoleService.removeWithRel(id);
        return Result.ok();
    }
}
