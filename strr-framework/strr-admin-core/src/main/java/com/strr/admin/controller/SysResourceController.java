package com.strr.admin.controller;

import com.strr.admin.model.SysResource;
import com.strr.admin.model.SysResourceVO;
import com.strr.admin.service.SysResourceService;
import com.strr.admin.util.SysUtil;
import com.strr.base.controller.SCrudController;
import com.strr.base.model.Result;
import com.strr.base.service.SCrudService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class SysResourceController extends SCrudController<SysResource, Integer> {
    private final SysResourceService sysResourceService;

    public SysResourceController(SysResourceService sysResourceService) {
        this.sysResourceService = sysResourceService;
    }

    @Override
    protected SCrudService<SysResource, Integer> getService() {
        return sysResourceService;
    }

    /**
     * 菜单树
     * @param param
     * @return
     */
    @GetMapping("/menuTree")
    public Result<List<SysResourceVO>> menuTree(SysResource param) {
        List<SysResource> sysAuthorityList = sysResourceService.listByParam(param);
        return Result.ok(SysUtil.buildMenuTree(sysAuthorityList));
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/removeInfo")
    public Result<Void> removeInfo(Integer id) {
        sysResourceService.removeWithRel(id);
        return Result.ok();
    }
}
