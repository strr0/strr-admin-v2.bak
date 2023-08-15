package com.strr.admin.controller;

import com.strr.admin.model.SysAuthority;
import com.strr.admin.model.SysAuthorityVO;
import com.strr.admin.service.SysAuthorityService;
import com.strr.admin.util.SysUtil;
import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.base.service.SCrudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/admin/sysAuthority")
public class SysAuthorityController extends SCrudController<SysAuthority, Integer> {
    private final SysAuthorityService sysAuthorityService;

    public SysAuthorityController(SysAuthorityService sysAuthorityService) {
        this.sysAuthorityService = sysAuthorityService;
    }

    @Override
    protected SCrudService<SysAuthority, Integer> getService() {
        return sysAuthorityService;
    }

    /**
     * 菜单树
     * @param param
     * @return
     */
    @GetMapping("/menuTree")
    @ResponseBody
    public Result<List<SysAuthorityVO>> menuTree(SysAuthority param) {
        List<SysAuthority> sysAuthorityList = sysAuthorityService.listByParam(param);
        return Result.ok(SysUtil.buildMenuTree(sysAuthorityList));
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/removeInfo")
    @ResponseBody
    public Result<Void> removeInfo(Integer id) {
        sysAuthorityService.removeWithRel(id);
        return Result.ok();
    }
}
